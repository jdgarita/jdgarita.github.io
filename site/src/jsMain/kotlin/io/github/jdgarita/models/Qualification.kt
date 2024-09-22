package io.github.jdgarita.models

import io.github.jdgarita.util.Res

internal enum class Qualification(
    val icon: String,
    val imageDesc: String,
    val title: String,
    val description: String
) {
    Android(
        icon = Res.Icon.android,
        imageDesc = "Android Icon",
        title = "Android Development",
        description = "Develop Android mobile applications utilizing Jetpack Compose for the user interface, with Kotlin Multiplatform (KMP) to manage shared business logic across platforms."
    ),
    IOS(
        icon = Res.Icon.apple,
        imageDesc = "Apple Icon",
        title = "iOS Development",
        description = "Develop iOS mobile applications utilizing SwiftUI for the user interface, with Kotlin Multiplatform (KMP) to manage shared business logic across platforms."
    ),
    Web(
        icon = Res.Icon.web,
        imageDesc = "Desktop Icon",
        title = "Web Development",
        description = "Develop Android mobile applications utilizing Kobweb for the user interface, with Kotlin Multiplatform (KMP) to manage shared business logic across platforms."
    ),
    Design(
        icon = Res.Icon.design,
        imageDesc = "Pen Icon",
        title = "UX/UI Design",
        description = "Specialize in creating intuitive, user-centered mobile apps with seamless, responsive interfaces. Focus on optimizing user flows and delivering high-performance apps that enhance user experience."
    ),
    Business(
        icon = Res.Icon.business,
        imageDesc = "Chart Icon",
        title = "Business Analysis",
        description = "Align mobile development with business goals by analyzing requirements, translating them into features, and ensuring apps deliver value through optimized functionality and user engagement."
    ),
    TestCoverage(
        icon = Res.Icon.automation,
        imageDesc = "Automation Icon",
        title = "Automation",
        description = "Improve software development flow by implementing automated solutions including scheduled unit and automated testing and the release process to the different app stores."
    )
}