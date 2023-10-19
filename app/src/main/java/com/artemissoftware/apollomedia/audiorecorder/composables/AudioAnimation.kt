package com.artemissoftware.apollomedia.audiorecorder.composables

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Percent
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.apollomedia.ui.theme.Purple80

@Composable
fun AudioAnimation(
    isAnimating: Boolean,
    modifier: Modifier = Modifier,
    imageVector: ImageVector = Icons.Filled.Percent,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val dy by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "",
    )

    val animation = if (isAnimating) dy else 0F

    val travelDistance = with(LocalDensity.current) { 30.dp.toPx() }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            imageVector = imageVector,
            "",
            tint = Purple80,
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.CenterHorizontally)
                .graphicsLayer {
                    translationY = animation * travelDistance
                },
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .width(40.dp)
                .height(10.dp)
                .align(Alignment.CenterHorizontally)
                .graphicsLayer {
                    scaleX = 0.5f + animation / 2
                    alpha = 0.3f + animation / 2
                },
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Purple80, shape = CircleShape),
            )
        }
    }
}

@Preview
@Composable
private fun AudioAnimationPreview() {
    AudioAnimation(
        true,
        Modifier
            .width(200.dp),
    )
}
