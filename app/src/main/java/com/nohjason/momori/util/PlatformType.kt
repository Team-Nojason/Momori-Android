package com.nohjason.momori.util

sealed class PlatformType(val typeName: String) {
    object Google: PlatformType(typeName = "G")
    object Naver: PlatformType(typeName = "N")
    object Kakao: PlatformType(typeName = "K")
}
