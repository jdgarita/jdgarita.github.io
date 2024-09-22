package io.github.jdgarita.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import io.github.jdgarita.components.ExperienceCard
import io.github.jdgarita.components.SectionTitle
import io.github.jdgarita.models.Experience
import io.github.jdgarita.models.Section
import io.github.jdgarita.util.Constants.SECTION_WIDTH
import io.github.jdgarita.util.ObserveViewportEntered
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px

@Composable
internal fun ExperienceSection() {
    Box(
        modifier =
        Modifier
            .id(Section.Experience.id)
            .maxWidth(SECTION_WIDTH.px)
            .padding(top = 100.px),
        contentAlignment = Alignment.Center,
    ) {
        ExperienceContent()
    }
}

@Composable
private fun ExperienceContent() {
    val breakpoint = rememberBreakpoint()
    var animatedMargin by remember { mutableStateOf(200.px) }

    ObserveViewportEntered(
        sectionId = Section.Experience.id,
        distanceFromTop = 500.0,
        onViewportEntered = {
            animatedMargin = 50.px
        },
    )

    Column(
        modifier =
        Modifier
            .fillMaxWidth(
                if (breakpoint >= Breakpoint.SM) {
                    100.percent
                } else {
                    90.percent
                },
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SectionTitle(
            modifier =
            Modifier
                .fillMaxWidth(
                    if (breakpoint >= Breakpoint.SM) {
                        60.percent
                    } else {
                        90.percent
                    },
                ).margin(bottom = 25.px),
            section = Section.Experience,
        )
        Experience.entries.forEach { experience ->
            ExperienceCard(
                breakpoint = breakpoint,
                active = experience == Experience.First,
                experience = experience,
                animatedMargin = animatedMargin,
            )
        }
    }
}