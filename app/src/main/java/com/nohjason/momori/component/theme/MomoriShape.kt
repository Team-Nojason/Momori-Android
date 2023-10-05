package com.nohjason.momori.component.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

class MomoriShape(
    val small: CornerBasedShape = RoundedCornerShape(5.dp),
    val medium: CornerBasedShape = RoundedCornerShape(10.dp),
    val large: CornerBasedShape = RoundedCornerShape(20.dp)
) {

    fun copy(
        small: CornerBasedShape = this.small,
        medium: CornerBasedShape = this.medium,
        large: CornerBasedShape = this.large
    ): MomoriShape = MomoriShape(
        small, medium, large
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MomoriShape) return false
        if (small != other.small) return false
        if (medium != other.medium) return false
        if (large != other.large) return false
        return true
    }

    override fun hashCode(): Int {
        var result = small.hashCode()
        result = 31 * result + medium.hashCode()
        result = 31 * result + large.hashCode()
        return result
    }

    override fun toString(): String {
        return "Sgx shapes(small=$small, medium=$medium, large=$large)"
    }
}

internal val LocalShape = staticCompositionLocalOf { MomoriShape() }
