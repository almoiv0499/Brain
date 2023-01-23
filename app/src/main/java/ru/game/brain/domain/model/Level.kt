package ru.game.brain.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class Level : Parcelable {
    TEST, LOW, MIDDLE, HIGH
}