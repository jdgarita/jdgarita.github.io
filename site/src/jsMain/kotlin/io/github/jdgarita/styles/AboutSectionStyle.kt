package io.github.jdgarita.styles

import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.css.TransitionProperty
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.opacity
import com.varabyte.kobweb.compose.ui.modifiers.rotate
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.selectors.hover
import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.*

@OptIn(ExperimentalComposeWebApi::class)
val AboutImageStyle =
    CssStyle {
        base {
            Modifier
                .styleModifier { filter { grayscale(100.percent) } }
                .borderRadius(r = 0.px)
                .rotate(0.deg)
                .transition(
                    Transition.of(
                        property = TransitionProperty.All,
                        duration = 200.ms,
                        timingFunction = null,
                        delay = null
                    )
                )
        }
        hover {
            Modifier
                .styleModifier { filter { grayscale(0.percent) } }
                .borderRadius(r = 100.px)
                .rotate(10.deg)
        }
    }

val AboutTextStyle =
    CssStyle {
        base {
            Modifier
                .opacity(50.percent)
                .transition(Transition.of(property = "opacity", duration = 200.ms, timingFunction = null, delay = null))
        }
        hover {
            Modifier.opacity(100.percent)
        }
    }
