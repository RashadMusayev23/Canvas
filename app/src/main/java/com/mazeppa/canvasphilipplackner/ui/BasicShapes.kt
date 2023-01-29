package com.mazeppa.canvasphilipplackner.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.mazeppa.canvasphilipplackner.ui.theme.Teal

/**
 * @author Rashad Musayev (https://github.com/RashadMusayev23) on 1/28/2023
 */
//Lesson Name: Basic Shapes
@Composable
fun MyCanvas() {
    Canvas(
        modifier = Modifier
            .padding(20.dp)
            .size(300.dp)
    ) {
        drawRect(
            Color.Black,
            size = size
        )
        drawRect(
            color = Color.Blue,
            topLeft = Offset(100f, 100f),
            size = Size(100f, 100f),
            style = Stroke(
                width = 3.dp.toPx()
            )
        )

        drawCircle(
            brush = Brush.radialGradient(
                listOf(Color.Red, Color.Yellow),
                center = center, //center of brush will be center of canvas, not circle,
                radius = 50f
            ),
            radius = 100f
        )

        drawArc(
            color = Teal,
            startAngle = 0f,
            sweepAngle = -270f,
            useCenter = true,
            topLeft = Offset(100f, 500f),
            size = Size(200f, 200f),
//            style = Stroke(
//                width = 3.dp.toPx()
//            )
        )

        drawLine(
            color = Color.Cyan,
            start = Offset(200f, 200f),
            end = Offset(500f, 500f),
            strokeWidth = 5.dp.toPx()
        )
    }

}