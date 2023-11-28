package com.vrushi.emiaggregator.feature_onboard.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vrushi.emiaggregator.R

@Composable
fun AppFlowScreen(onNextScreen: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.weight(1f).fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Image(modifier = Modifier
                .height(160.dp)
                .width(160.dp),painter = painterResource(id = R.drawable.ic_android_black_24dp), contentDescription = "Application Logo")
        }
        Column(
            modifier = Modifier.weight(1f).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(stringResource(id = R.string.app_flow_detail_text), textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(4.dp))
            Button(onClick = { onNextScreen() }) {
                Text(stringResource(id = R.string.app_flow_button_next))
            }
        }
    }
}