import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.Navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.rafaelanastacioalves.moby.R
import com.example.rafaelanastacioalves.moby.application.MainApplication
import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity
import com.example.rafaelanastacioalves.moby.domain.interactors.MainEntityListInteractor
import com.example.rafaelanastacioalves.moby.ui.entitymainlisting.MainScreenViewModel
import com.example.rafaelanastacioalves.moby.ui.entitymainlisting.MainScreenViewModelInterface

@Composable
fun MainScreen(viewModel: MainScreenViewModelInterface, onNavigate: (Int) -> Unit) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                }
            )
        }
    ) {
        val viewState = viewModel.mainEntityListLiveData.observeAsState()
        LazyColumn(modifier = Modifier.fillMaxSize()
            .testTag("list"), contentPadding = it, ) {
            items(items = viewState.value?.stateList.orEmpty()) { mainEntity ->
                MainEntityListItem(mainEntity = mainEntity, modifier = Modifier.clickable {
                    onNavigate(R.id.entityDetailsFragment)
                })
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MainEntityListItem(modifier: Modifier = Modifier,mainEntity: MainEntity) {
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
