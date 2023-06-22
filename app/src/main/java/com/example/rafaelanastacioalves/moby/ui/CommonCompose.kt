package com.example.rafaelanastacioalves.moby.ui

import android.app.ActivityManager.ProcessErrorStateInfo
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.rafaelanastacioalves.moby.R

@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    hasNavigationUp: Boolean = false,
    textTitle: String,
    onNavigationUp: (() -> Unit) = {},
) {

    TopAppBar(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
        elevation = 0.dp,
        title = { Text(text = textTitle) },
        navigationIcon = {
            if (hasNavigationUp) Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(id = R.string.goBack),
                modifier = modifier.clickable(onClick = onNavigationUp)
            )
        }
    )
}

@Composable
fun LoadingScreen(modifier: Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center)
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(modifier: Modifier, message: String) {
    Surface(modifier = modifier) {
        Text(
            text = message
        )

    }
}