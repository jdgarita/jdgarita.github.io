package io.github.jdgarita.models

enum class Experience(
    val number: String,
    val jobPosition: String,
    val description: String,
    val company: String,
    val from: String,
    val to: String
) {
    First(
        number = "01",
        jobPosition = "Senior Kotlin Multiplatform Developer",
        description = "Collaborate on the development and maintenance of a mobile app factory, focusing primarily on the Kotlin Multiplatform ads component used by multiple retailers. I am responsible for implementing telemetry events such as impressions and click events, as well as integrating various ad providers, including Xandr and Google Ads. Additionally, I ensure the reliability and functionality of the system by writing and maintaining unit and automated tests.",
        company = "Swiftly",
        from = "2022",
        to = "Present"
    ),
    Second(
        number = "02",
        jobPosition = "Senior Android Developer",
        description = "Worked on various components aimed at promoting user engagement and increasing time spent within the app through activities such as completing surveys, reading news, and watching videos. I was responsible for implementing multiple SDKs from different providers behind a common layer, which simplified maintenance. Additionally, I enabled or disabled different providers based on business needs using feature flags.",
        company = "Mode Mobile",
        from = "2021",
        to = "2022"
    ),
    Third(
        number = "03",
        jobPosition = "Mobile Developer",
        description = "Collaborated closely with the Experience Team in designing and developing new features for a companion mobile app for a web-based solution that authenticated users through biometrics and QR/barcode scanning, ensuring no critical user data was compromised nor stored in the device.",
        company = "Trusona",
        from = "2017",
        to = "2021"
    )
}