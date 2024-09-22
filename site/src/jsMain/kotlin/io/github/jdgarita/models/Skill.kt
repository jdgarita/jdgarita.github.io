package io.github.jdgarita.models

import org.jetbrains.compose.web.css.CSSSizeValue
import org.jetbrains.compose.web.css.CSSUnit
import org.jetbrains.compose.web.css.percent

internal enum class Skill(
    val title: String,
    val percentage: CSSSizeValue<CSSUnit.percent>,
) {
    JetpackCompose(
        title = "Jetpack Compose",
        percentage = 95.percent,
    ),
    Coroutines(
        title = "Coroutines",
        percentage = 90.percent,
    ),
    Gradle(
        title = "Gradle",
        percentage = 90.percent,
    ),
    SwiftUI(
        title = "SwiftUI",
        percentage = 75.percent,
    ),
    GraphQL(
        title = "GraphQL",
        percentage = 75.percent
    )
}