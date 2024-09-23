package io.github.jdgarita.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.FontStyle
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import io.github.jdgarita.components.SectionTitle
import io.github.jdgarita.components.SkillBar
import io.github.jdgarita.models.Section
import io.github.jdgarita.models.Skill
import io.github.jdgarita.models.Theme
import io.github.jdgarita.styles.AboutImageStyle
import io.github.jdgarita.styles.AboutTextStyle
import io.github.jdgarita.util.Constants.FONT_FAMILY
import io.github.jdgarita.util.Constants.SECTION_WIDTH
import io.github.jdgarita.util.ObserveViewportEntered
import io.github.jdgarita.util.Res
import io.github.jdgarita.util.animateNumbers
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Composable
internal fun AboutSection() {
    Box(
        modifier =
        Modifier
            .id(Section.About.id)
            .maxWidth(SECTION_WIDTH.px)
            .padding(top = 100.px),
        contentAlignment = Alignment.Center,
    ) {
        AboutContent()
    }
}

@Composable
private fun AboutContent() {
    val breakpoint = rememberBreakpoint()
    Column(
        modifier =
        Modifier
            .fillMaxWidth(
                if (breakpoint >= Breakpoint.MD) {
                    100.percent
                } else {
                    90.percent
                },
            ).maxWidth(1200.px),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SimpleGrid(
            modifier =
            Modifier.fillMaxWidth(
                if (breakpoint >= Breakpoint.MD) {
                    90.percent
                } else {
                    100.percent
                },
            ),
            numColumns = numColumns(base = 1, md = 2),
        ) {
            if (breakpoint >= Breakpoint.MD) {
                AboutImage()
            }
            AboutMe()
        }
    }
}

@Composable
private fun AboutImage() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            modifier =
            AboutImageStyle
                .toModifier()
                .fillMaxWidth(80.percent),
            src = Res.Image.certification,
            alt = "Google Certification Badge",
        )
    }
}

@Composable
private fun AboutMe() {
    val scope = rememberCoroutineScope()
    var viewportEntered by remember { mutableStateOf(false) }
    val animatedPercentage = remember { mutableStateListOf(0, 0, 0, 0, 0) }

    ObserveViewportEntered(
        sectionId = Section.About.id,
        distanceFromTop = 300.0,
        onViewportEntered = {
            viewportEntered = true
            Skill.entries.forEach { skill ->
                scope.launch {
                    animateNumbers(
                        number = skill.percentage.value.toInt(),
                        onUpdate = {
                            animatedPercentage[skill.ordinal] = it
                        },
                    )
                }
            }
        },
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
    ) {
        SectionTitle(section = Section.About)
        P(
            attrs =
            AboutTextStyle
                .toModifier()
                .margin(topBottom = 25.px)
                .maxWidth(500.px)
                .fontFamily(FONT_FAMILY)
                .fontSize(18.px)
                .fontWeight(FontWeight.Normal)
                .fontStyle(FontStyle.Italic)
                .color(Theme.Secondary.rgb)
                .toAttrs(),
        ) {
            Text(
                "I’m a Google Certified Associate Android Developer with over 9 years of experience crafting clean, high-quality code for both large corporations and startups. With a strong foundation in agile methodologies like Scrum and Kanban, I excel at developing and designing new features, collaborating with cross-functional teams, and enhancing existing codebases. My focus is on delivering robust, maintainable solutions, while ensuring seamless testing, debugging, and performance optimization throughout the development process.",
            )
        }
        Skill.entries.forEach { skill ->
            SkillBar(
                title = skill.title,
                index = skill.ordinal,
                percentage = if (viewportEntered) skill.percentage else 0.percent,
                animatedPercentage = if (viewportEntered) animatedPercentage[skill.ordinal] else 0,
            )
        }
    }
}