package com.nohjason.momori.component.extension

import androidx.compose.ui.graphics.Color


/**
 * @return AARRGGBB : String
 */
fun Color.toHexString(): String {
    return String.format("%X", this.alpha) + String.format("%X", this.red) + String.format("%X", this.green) + String.format("%X", this.blue)
}