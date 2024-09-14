package io.github.jdgarita.models

enum class Section(
    val id: String,
    val title: String,
    val subtitle: String,
    val path: String
) {
    Home(
        id = "home",
        title = "Home",
        subtitle = "",
        path = "#home"
    ),
    About(
        id = "about",
        title = "About me",
        subtitle = "Juan Diego",
        path = "#about"
    ),
    Experience(
        id = "experience",
        title = "Experience",
        subtitle = "Work Experience",
        path = "#experience"
    ),
    Qualifications(
        id = "qualifications",
        title = "Qualifications",
        subtitle = "I'm Good at",
        path = "#qualifications"
    ),
    Resume(
        id = "resume",
        title = "Resume",
        subtitle = "",
        path = "#resume"
    ),
    Contact(
        id = "contact",
        title = "Contact me",
        subtitle = "Get in Touch",
        path = "#contact"
    ),
    Skills(
        id = "skills",
        title = "Skills",
        subtitle = "",
        path = "#skills"
    )
}