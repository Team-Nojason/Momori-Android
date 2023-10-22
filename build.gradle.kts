buildscript {
    dependencies {
        classpath ("org.jetbrains.kotlin:kotlin-serialization:1.7.20")
    }
}

plugins {
    id ("com.android.application") version "8.0.2" apply false
    id ("com.android.library") version "8.0.2" apply false
    id ("org.jetbrains.kotlin.android") version "1.7.20" apply false
}