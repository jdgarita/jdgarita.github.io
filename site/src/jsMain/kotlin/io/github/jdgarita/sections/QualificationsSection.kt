package io.github.jdgarita.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import io.github.jdgarita.components.QualificationCard
import io.github.jdgarita.components.SectionTitle
import io.github.jdgarita.models.Qualification
import io.github.jdgarita.models.Section
import io.github.jdgarita.util.Constants.SECTION_WIDTH
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px

@Composable
internal fun QualificationsSection() {
    Box(
        modifier = Modifier
            .id(Section.Qualifications.id)
            .maxWidth(SECTION_WIDTH.px)
            .padding(top = 100.px),
        contentAlignment = Alignment.Center
    ) {
        QualificationContent()
    }
}

@Composable
private fun QualificationContent() {
    val breakpoint = rememberBreakpoint()
    Column(
        modifier = Modifier
            .fillMaxWidth(
                if (breakpoint >= Breakpoint.MD) 100.percent
                else 90.percent
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SectionTitle(
            modifier = Modifier
                .fillMaxWidth()
                .margin(bottom = 20.px),
            section = Section.Qualifications,
            alignment = Alignment.CenterHorizontally
        )
        SimpleGrid(numColumns = numColumns(base = 1, sm = 2, md = 3)) {
            Qualification.entries.forEach { qualification ->
                QualificationCard(qualification = qualification)
            }
        }
    }
}