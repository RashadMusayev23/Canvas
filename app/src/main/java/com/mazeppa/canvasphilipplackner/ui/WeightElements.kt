package com.mazeppa.canvasphilipplackner.ui

import android.graphics.Color
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.core.graphics.withRotation
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

/**
 * @author Rashad Musayev (https://github.com/RashadMusayev23) on 1/28/2023
 */

@Composable
fun Scale(
    modifier: Modifier,
    style: ScaleStyle = ScaleStyle(),
    minWeight: Int = 20,
    maxWeight: Int = 250,
    initialWeight: Int = 80,
    onWeightChange: (Int) -> Unit
) {
    val radius = style.radius
    val scaleWidth = style.scaleWidth

    //Center of Canvas
    var center by remember {
        mutableStateOf(Offset.Zero)
    }

    var circleCenter by remember {
        mutableStateOf(Offset.Zero)
    }

    //Angle scale is rotated
    var angle by remember {
        mutableStateOf(0f)
    }

    var dragStartedAngle by remember {
        mutableStateOf(0f)
    }

    var oldAngle by remember {
        mutableStateOf(angle)
    }

    Canvas(modifier = modifier
        .pointerInput(true) {
            detectDragGestures(
                onDragStart = { offset ->
                    dragStartedAngle = -atan2(
                        circleCenter.x - offset.x,
                        circleCenter.y - offset.y
                    ) * (180 / PI.toFloat())
                },
                onDragEnd = {
                    oldAngle = angle
                }
            ) { change, _ ->
                val touchAngle = -atan2(
                    circleCenter.x - change.position.x,
                    circleCenter.y - change.position.y
                ) * (180 / PI.toFloat())

                val newAngle = oldAngle + (touchAngle -dragStartedAngle)
                angle = newAngle.coerceIn(
                    minimumValue = initialWeight - maxWeight.toFloat(),
                    maximumValue = initialWeight - minWeight.toFloat()
                )
                onWeightChange(((initialWeight - angle).roundToInt()))
            }
        }
    ) {
        center = this.center
        circleCenter = Offset(center.x, scaleWidth.toPx() / 2f + radius.toPx())

        val outerRadius = radius.toPx() + scaleWidth.toPx() / 2f
        val innerRadius = radius.toPx() - scaleWidth.toPx() / 2f

        drawContext.canvas.nativeCanvas.apply {
            drawCircle(
                circleCenter.x,
                circleCenter.y,
                radius.toPx(),
                Paint().apply {
                    strokeWidth = scaleWidth.toPx()
                    //This makes circle consist of only stroke
                    setStyle(Paint.Style.STROKE)
                    //Color of circle
                    color = Color.WHITE
                    setShadowLayer(
                        60f,
                        0f,
                        0f,
                        Color.argb(50, 0, 0, 0)
                    )
                }
            )

        }


        for (i in minWeight..maxWeight) {
            //angle is how many angle we rotate scale and every time it changes, line is redrawn.
            //If user rotates scale 5 degree right, then every line should be drawn 5 degree right.
            val angleInRad = (i - initialWeight + angle - 90) * (PI / 180).toFloat()

            val lineType = when {
                i % 10 == 0 -> LineType.TenStep
                i % 5 == 0 -> LineType.FiveStep
                else -> LineType.Normal
            }

            val lineLength = when (lineType) {
                LineType.TenStep -> style.tenStepLineLength.toPx()
                LineType.FiveStep -> style.fiveStepLineLength.toPx()
                else -> style.normalLineLength.toPx()
            }

            val lineColor = when (lineType) {
                LineType.TenStep -> style.tenStepLineColor
                LineType.FiveStep -> style.fiveStepLineColor
                else -> style.normalLineColor
            }

            //Bottom point of indicator line
            val startPoint = Offset(
                x = (outerRadius - lineLength) * cos(angleInRad) + circleCenter.x,
                y = (outerRadius - lineLength) * sin(angleInRad) + circleCenter.y
            )

            //Top point of indicator line
            val endPoint = Offset(
                x = outerRadius * cos(angleInRad) + circleCenter.x,
                y = outerRadius * sin(angleInRad) + circleCenter.y
            )

            drawContext.canvas.nativeCanvas.apply {
                if (lineType == LineType.TenStep) {
                    val textRadius = outerRadius - lineLength - 5.dp.toPx() - style.textSize.toPx()
                    val x = textRadius * cos(angleInRad) + circleCenter.x
                    val y = textRadius * sin(angleInRad) + circleCenter.y
                    withRotation(
                        degrees = angleInRad * (180 / PI.toFloat()) + 90f,
                        pivotX = x,
                        pivotY = y
                    ) {
                        drawText(
                            abs(i).toString(),
                            x,
                            y,
                            Paint().apply {
                                textSize = style.textSize.toPx()
                                //If textAlign is not specified, x and y will be start position of text
                                textAlign = Paint.Align.CENTER
                            }
                        )
                    }
                }
            }

            drawLine(
                lineColor,
                startPoint,
                endPoint,
                1.dp.toPx()
            )

            drawIndicator(circleCenter, innerRadius, style)
        }
    }
}

private fun DrawScope.drawIndicator(circleCenter: Offset, innerRadius: Float, style: ScaleStyle) {
    val middleTop = Offset(
        x = circleCenter.x,
        y = circleCenter.y - innerRadius - style.scaleIndicatorLength.toPx()
    )

    val bottomLeft = Offset(
        x = circleCenter.x - 6f,
        y = circleCenter.y - innerRadius
    )

    val bottomRight = Offset(
        x = circleCenter.x + 6f,
        y = circleCenter.y - innerRadius
    )

    val indicator = Path().apply {
        moveTo(middleTop.x, middleTop.y)
        lineTo(bottomLeft.x, bottomLeft.y)
        lineTo(bottomRight.x, bottomRight.y)
        lineTo(middleTop.x, middleTop.y)
    }

    drawPath(
        indicator,
        style.scaleIndicatorColor
    )
}