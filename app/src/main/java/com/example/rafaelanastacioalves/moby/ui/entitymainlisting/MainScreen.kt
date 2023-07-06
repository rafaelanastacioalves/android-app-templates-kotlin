import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.rafaelanastacioalves.moby.R
import com.example.rafaelanastacioalves.moby.application.MainApplication
import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.domain.interactors.MainEntityListInteractor
import com.example.rafaelanastacioalves.moby.ui.entitymainlisting.MainScreenViewModel
import com.example.rafaelanastacioalves.moby.ui.entitymainlisting.MainScreenViewModelInterface

@Composable
fun MainScreen(viewModel: MainScreenViewModelInterface, onNavigate: (String) -> Unit) {

    Scaffold(
        topBar = {
            com.example.rafaelanastacioalves.moby.ui.AppBar(
                modifier = Modifier,
                textTitle = stringResource(id = R.string.app_name)
            )
        }
    ) {
        val viewState = remember { viewModel.mainScreeViewState.value }
        val status =
            viewState.status.value
        when (status) {
            Resource.Status.SUCCESS -> List(it, onNavigate, viewState.stateList)
            Resource.Status.INTERNAL_SERVER_ERROR -> TODO()
            Resource.Status.GENERIC_ERROR -> TODO()
            Resource.Status.LOADING -> Text(
                modifier = Modifier,
                text = stringResource(R.string.loading)
            )

            null -> {}
        }

    }
}

@Composable
private fun List(
    it: PaddingValues,
    onNavigate: (String) -> Unit,
    list: List<MainEntity>,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("list"),
        contentPadding = it,
        state = rememberLazyListState()
    ) {
        items(items = list) { mainEntity ->
            MainEntityListItem(mainEntity = mainEntity, modifier = Modifier.clickable {
                onNavigate(mainEntity.id)
            })
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MainEntityListItem(modifier: Modifier = Modifier, mainEntity: MainEntity) {
    Column(modifier) {
        GlideImage(model = mainEntity.imageUrl, contentDescription = mainEntity.title)
        Text(modifier = modifier, text = mainEntity.title)
    }
}

@Preview
@Composable
fun PreviewMainScreen() {
    val respository = (LocalContext.current as MainApplication).getAppRepository
    val viewModel = MainScreenViewModel(MainEntityListInteractor(respository))

    MainScreen(viewModel = viewModel, {})
}
