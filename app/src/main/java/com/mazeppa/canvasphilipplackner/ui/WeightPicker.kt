package com.mazeppa.canvasphilipplackner.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mazeppa.canvasphilipplackner.ui.theme.Teal

/**
 * @author Rashad Musayev (https://github.com/RashadMusayev23) on 1/28/2023
 */
@Composable
fun WeightPicker() {

    var kilogram by remember {
        mutableStateOf(80)
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)/*.align(Alignment.TopCenter)*/
        )

        Text(
//                    modifier = Modifier
//                        .align(Alignment.TopCenter),
            text = "Select your weight",
            fontSize = 34.sp,
            fontWeight = FontWeight.ExtraLight
        )

        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("$kilogram ")
                }

                withStyle(
                    style = SpanStyle(
                        color = Teal,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal
                    )
                ) {
                    append("KG")
                }
            }
        )

        Spacer(modifier = Modifier.size(100.dp))

        Scale(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
//                        .align(Alignment.Center),
            style = ScaleStyle(
                scaleWidth = 150.dp
            )
        ) { newKilogram ->
            kilogram = newKilogram
        }

        Button(
            shape = RoundedCornerShape(10.dp),
            onClick = {},
//                    modifier = Modifier
//                        .align(Alignment.BottomCenter),
            colors = ButtonDefaults.buttonColors(Teal),
        ) {
            Text(text = "NEXT", color = Color.White)
        }
    }

}