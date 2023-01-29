package com.mazeppa.canvasphilipplackner.ui

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mazeppa.canvasphilipplackner.ui.theme.Teal

/**
 * @author Rashad Musayev (https://github.com/RashadMusayev23) on 1/28/2023
 */
data class ScaleStyle(
    val scaleWidth : Dp = 100.dp,
    val radius: Dp = 550.dp,
    val normalLineColor : Color = Color.LightGray,
    val fiveStepLineColor : Color = Teal,
    val tenStepLineColor : Color = Color.Black,
    val normalLineLength: Dp = 15.dp,
    val fiveStepLineLength : Dp = 25.dp,
    val tenStepLineLength: Dp = 35.dp,
    val scaleIndicatorColor: Color = Teal,
    val scaleIndicatorLength: Dp = 60.dp,
    val textSize: TextUnit = 18.sp
)
