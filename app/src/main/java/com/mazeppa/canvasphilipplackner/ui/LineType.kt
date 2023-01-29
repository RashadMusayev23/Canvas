package com.mazeppa.canvasphilipplackner.ui

/**
 * @author Rashad Musayev (https://github.com/RashadMusayev23) on 1/29/2023
 */
sealed class LineType {
    object TenStep : LineType()
    object FiveStep : LineType()
    object Normal : LineType()
}
