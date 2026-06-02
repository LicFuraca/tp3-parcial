package com.example.parcial_grupo_4.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.parcial_grupo_4.R
import com.example.parcial_grupo_4.ui.theme.*
import com.example.parcial_grupo_4.ui.common.LendlyBottomAction

@Composable
fun PantallaDone(onGetStarted: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LendlyColors.Background.Screen)
            .padding(vertical = LendlySpacing.Spacing4),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, start = 12.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_close_cross),
                contentDescription = "Close",
                modifier = Modifier.size(48.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(id = R.drawable.ic_logo_layers),
                contentDescription = "Logo",
                modifier = Modifier.size(width = 116.dp, height = 40.dp)
            )

            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.size(48.dp))
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_done_check),
                contentDescription = null,
                modifier = Modifier.size(width = 165.dp, height = 300.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))


            Image(
                painter = painterResource(id = R.drawable.img_all_done_text),
                contentDescription = "All done",
                modifier = Modifier.size(width = 230.dp, height = 36.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))


            Image(
                painter = painterResource(id = R.drawable.img_ready_text),
                contentDescription = "Ready to start",
                modifier = Modifier.size(width = 257.dp, height = 25.dp)
            )
        }


        LendlyBottomAction(
            text = "Done",
            onClick = onGetStarted,
            showDivider = false
        )
    }
}