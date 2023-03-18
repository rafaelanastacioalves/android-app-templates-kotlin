import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.rafaelanastacioalves.moby.R
import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity
import com.example.rafaelanastacioalves.moby.ui.entitymainlisting.MainScreenViewModel

@Composable
fun MainScreen(viewModel: MainScreenViewModel) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                }
            )
        }
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = it) {
            items(items = viewModel.entityList) { mainEntity ->
                MainEntityListItem(mainEntity = mainEntity)
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
    val viewModel = MainScreenViewModel(/* passe aqui os argumentos necessários */)

    MainScreen(viewModel = viewModel)
}
