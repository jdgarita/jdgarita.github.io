package io.github.jdgarita.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.navigation.OpenLinkStrategy
import com.varabyte.kobweb.silk.components.icons.fa.FaGithub
import com.varabyte.kobweb.silk.components.icons.fa.FaLinkedin
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.style.toModifier
import io.github.jdgarita.styles.SocialLinkStyle
import io.github.jdgarita.util.Constants.GITHUB
import io.github.jdgarita.util.Constants.LINKEDIN
import org.jetbrains.compose.web.css.px

@Composable
internal fun SocialBar() {
    Row(
        modifier =
        Modifier
            .padding(leftRight = 20.px)
            .minHeight(40.px)
            .borderRadius(r = 20.px)
            .backgroundColor(Colors.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        Link(
            path = LINKEDIN,
            openExternalLinksStrategy = OpenLinkStrategy.IN_NEW_TAB,
        ) {
            FaLinkedin(
                modifier =
                SocialLinkStyle
                    .toModifier()
                    .margin(right = 20.px),
                size = IconSize.LG,
            )
        }
        Link(
            path = GITHUB,
            openExternalLinksStrategy = OpenLinkStrategy.IN_NEW_TAB,
        ) {
            FaGithub(
                modifier = SocialLinkStyle.toModifier(),
                size = IconSize.LG,
            )
        }
    }
}
