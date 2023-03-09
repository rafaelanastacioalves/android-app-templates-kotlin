package com.example.rafaelanastacioalves.moby.ui.entitydetailing

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.rafaelanastacioalves.moby.R
import com.example.rafaelanastacioalves.moby.domain.entities.EntityDetails
import com.example.rafaelanastacioalves.moby.domain.entities.Resource

@Composable
fun EntityDetailsCompose(viewModel: EntityDetailsViewModel) {
    val entityDetailsResource by viewModel.entityDetails.observeAsState()
    entityDetailsResource?.let { resource ->
        when(resource.status){
            Resource.Status.SUCCESS -> resource.data?.let { EntityDetailContent(entityDetail = it) }
            Resource.Status.INTERNAL_SERVER_ERROR -> TODO()
            Resource.Status.GENERIC_ERROR -> TODO()
            Resource.Status.LOADING -> Unit
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun EntityDetailContent(entityDetail: EntityDetails) {
    Surface(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            GlideImage(modifier = Modifier.fillMaxWidth() , contentScale = ContentScale.Crop, model = entityDetail.imageUrl, contentDescription = entityDetail.description){
                it.placeholder(R.drawable.ic_placeholder_map_selector)
            }
            Text(text = entityDetail.title)
        }
    }
}


