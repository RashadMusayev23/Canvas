package com.mazeppa.canvasphilipplackner.ui

import android.graphics.Color
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.nativeCanvas

/**
 * @author Rashad Musayev (https://github.com/RashadMusayev23) on 1/28/2023
 */

@Composable
fun DrawText() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawContext.canvas.nativeCanvas.apply {
            drawText(
                "Hello World",
                100f,
                100f,
                Paint().apply {
                    color = Color.RED
                    textSize = 100f
                }
            )
        }
    }
}