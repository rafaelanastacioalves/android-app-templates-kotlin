package com.example.rafaelanastacioalves.moby.ui.entitydetailing

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.rafaelanastacioalves.moby.R
import com.example.rafaelanastacioalves.moby.domain.entities.EntityDetails
import com.example.rafaelanastacioalves.moby.domain.entities.Resource
import com.example.rafaelanastacioalves.moby.ui.AppBar
import com.example.rafaelanastacioalves.moby.ui.ErrorScreen
import com.example.rafaelanastacioalves.moby.ui.LoadingScreen

@Composable
fun EntityDetailsCompose(viewModel: EntityDetailsViewModel, goBack : () -> Unit) {
    val entityDetailsResource by viewModel.entityDetails.observeAsState()
    Scaffold(modifier = Modifier,
    topBar = { AppBar(textTitle = "Entity Details", hasNavigationUp = true, onNavigationUp = goBack) }) { padding ->

        entityDetailsResource?.let { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS -> resource.data?.let { EntityDetailContent(modifier = Modifier.padding(padding), entityDetail = it) }
                Resource.Status.INTERNAL_SERVER_ERROR -> ErrorScreen(modifier = Modifier, message = "${ stringResource(id = R.string.error) }: \n" + resource.message.orEmpty())
                Resource.Status.GENERIC_ERROR -> ErrorScreen(modifier = Modifier, message = "${ stringResource(id = R.string.error) }: \n" + resource.message.orEmpty())
                Resource.Status.LOADING -> LoadingScreen(modifier = Modifier.padding(padding))
            }
        }
    }
}

@Composable
private fun EntityDetailContent(modifier: Modifier, entityDetail: EntityDetails) {
    Surface(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                modifier = modifier.fillMaxWidth().testTag("DetailImage"),
                contentScale = ContentScale.Fit,
                model = entityDetail.imageUrl,
                contentDescription = entityDetail.description,
                placeholder = painterResource(id = R.drawable.ic_placeholder_map),
                error = painterResource(id = R.drawable.ic_placeholder_map)
            )
            Text(text = entityDetail.title)
        }
    }

}


