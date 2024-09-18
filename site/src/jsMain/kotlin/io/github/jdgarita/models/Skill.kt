package io.github.jdgarita.models

import org.jetbrains.compose.web.css.CSSSizeValue
import org.jetbrains.compose.web.css.CSSUnit
import org.jetbrains.compose.web.css.percent

enum class Skill(
    val title: String,
    val percentage: CSSSizeValue<CSSUnit.percent>
) {
    Kotlin(
        title = "Kotlin",
        percentage = 95.percent
    ),
    Coroutines(
        title = "Coroutines",
        percentage = 90.percent
    ),
    JetpackCompose(
        title = "Jetpack Compose",
        percentage = 85.percent
    ),
    Swift(
        title = "Swift",
        percentage = 75.percent
    ),
    SwiftUI(
        title = "Swift UI",
        percentage = 75.percent
    )
}