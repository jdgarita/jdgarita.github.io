package io.github.jdgarita.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import io.github.jdgarita.models.Section
import io.github.jdgarita.models.Theme
import io.github.jdgarita.styles.NavigationItemStyle
import io.github.jdgarita.util.Constants.FONT_FAMILY
import io.github.jdgarita.util.Constants.SECTION_WIDTH
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Composable
internal fun FooterSection() {
    Box(
        modifier = Modifier
            .id(Section.Contact.id)
            .fillMaxWidth()
            .maxWidth(SECTION_WIDTH.px)
            .padding(top = 100.px, bottom = 50.px)
            .backgroundColor(Theme.LighterGray.rgb),
        contentAlignment = Alignment.Center
    ) {
        FooterContent()
    }
}

@Composable
private fun FooterContent() {
    val breakpoint = rememberBreakpoint()
    Column(
        modifier = Modifier
            .fillMaxWidth(
                if (breakpoint >= Breakpoint.SM) 100.percent
                else 90.percent
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        P(
            attrs =
            Modifier
                .fillMaxWidth()
                .margin(0.px)
                .textAlign(TextAlign.Center)
                .fontFamily(FONT_FAMILY)
                .fontSize(40.px)
                .fontWeight(FontWeight.Bold)
                .color(Theme.Secondary.rgb)
                .transition(Transition.of(property = "margin", duration = 300.ms))
                .toAttrs(),
        ) {
            Text("JD")
        }
        P(
            attrs =
            Modifier
                .textAlign(TextAlign.Center)
                .fontFamily(FONT_FAMILY)
                .margin(bottom = 100.px)
                .fontSize(14.px)
                .fontWeight(FontWeight.Normal)
                .color(Theme.Primary.rgb)
                .toAttrs(),
        ) {
            Text("juandiegogarita@gmail.com")
        }
        if (breakpoint > Breakpoint.SM) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                FooterMenu()
            }
        } else {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FooterMenu(row = false)
            }
        }
    }
}

@Composable
private fun FooterMenu(row: Boolean = true) {
    Section.entries.toTypedArray().take(6).forEach { section ->
        Link(
            modifier = NavigationItemStyle.toModifier()
                .fontFamily(FONT_FAMILY)
                .padding(
                    right = if (row) 20.px else 0.px,
                    bottom = if (row) 0.px else 20.px
                )
                .fontSize(12.px)
                .fontWeight(FontWeight.Normal)
                .textDecorationLine(TextDecorationLine.None),
            path = section.path,
            text = section.title
        )
    }
}
