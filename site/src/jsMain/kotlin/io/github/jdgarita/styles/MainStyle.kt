package io.github.jdgarita.styles

import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.transform
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.selectors.hover
import io.github.jdgarita.models.Theme
import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.*

val NavigationItemStyle =
    CssStyle {
        base {
            Modifier
                .styleModifier {
                    property("--bs-link-color", Theme.Secondary.rgb)
                }.color(Theme.Secondary.rgb)
                .transition(Transition.of(property = "color", duration = 200.ms))
        }
        hover {
            Modifier
                .styleModifier {
                    property("--bs-link-hover-color", Theme.Primary.rgb)
                }.color(Theme.Primary.rgb)
        }
    }

val MiniMeStyle =
    CssStyle {
        base {
            Modifier
                .transform { rotate(0.deg) }
                .transition(Transition.of(property = "transform", duration = 200.ms))
        }
        hover {
            Modifier
                .transform { rotate((-10).deg) }
        }
    }

val SocialLinkStyle =
    CssStyle {
        base {
            Modifier
                .color(Theme.Gray.rgb)
                .transition(Transition.of(property = "color", duration = 200.ms, timingFunction = null, delay = null))
        }
        hover {
            Modifier.color(Theme.Primary.rgb)
        }
    }

val MainButtonStyle =
    CssStyle {
        base {
            Modifier
                .width(100.px)
                .transition(Transition.of(property = "width", duration = 200.ms, timingFunction = null, delay = null))
        }
        hover {
            Modifier.width(120.px)
        }
    }

@OptIn(ExperimentalComposeWebApi::class)
val MainImageStyle =
    CssStyle {
        base {
            Modifier
                .styleModifier {
                    filter { grayscale(100.percent) }
                }.transition(Transition.of(property = "filter", duration = 200.ms, timingFunction = null, delay = null))
        }
        hover {
            Modifier.styleModifier {
                filter { grayscale(0.percent) }
            }
        }
    }
