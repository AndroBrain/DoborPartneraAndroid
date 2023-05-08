package ee.pw.edu.pl.doborpartnera.ui.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable

const val ENTER_ANIM_MILLIS = 250
const val EXIT_ANIM_MILLIS = 150

fun NavGraphBuilder.composable(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    transitions: Transitions = Transitions(),
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = route,
        arguments = arguments,
        deepLinks = deepLinks,
        enterTransition = transitions.enterTransition,
        exitTransition = transitions.exitTransition,
        popEnterTransition = transitions.popEnterTransition,
        popExitTransition = transitions.popExitTransition,
        content = content,
    )
}

data class Transitions(
    val enterTransition: (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?)? = null,
    val exitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?)? = null,
    val popEnterTransition: (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?)? = enterTransition,
    val popExitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?)? = exitTransition,
)

val fadeTransitions = Transitions(
    enterTransition = { fadeIn() },
    exitTransition = { fadeOut() },
    popEnterTransition = { fadeIn() },
    popExitTransition = { fadeOut() },
)

val scaleTransitions = Transitions(
    enterTransition = { scaleUp() },
    exitTransition = { fadeOut() },
    popEnterTransition = { fadeIn() },
    popExitTransition = { scaleDown() },
)

val slideVerticallyTransitions = Transitions(
    enterTransition = { slideInFromTop() },
    exitTransition = { fadeOut() },
    popEnterTransition = { fadeIn() },
    popExitTransition = { slideOutToBottom() },
)

@OptIn(ExperimentalAnimationApi::class)
fun scaleDown() = scaleOut(
    animationSpec = tween(
        durationMillis = EXIT_ANIM_MILLIS,
        easing = LinearEasing
    ),
    targetScale = 0.8f,
) + fadeOut(
    animationSpec = tween(
        durationMillis = EXIT_ANIM_MILLIS,
        easing = LinearEasing,
    )
)

@OptIn(ExperimentalAnimationApi::class)
fun scaleUp() = scaleIn(
    animationSpec = tween(
        durationMillis = ENTER_ANIM_MILLIS,
        easing = LinearEasing
    ),
    initialScale = 0.8f,
) + fadeIn(
    animationSpec = tween(
        durationMillis = ENTER_ANIM_MILLIS,
        easing = LinearEasing,
    )
)

fun slideOutToBottom() = slideOutVertically(targetOffsetY = { it / 2 }) + fadeOut(
    animationSpec = tween(
        durationMillis = EXIT_ANIM_MILLIS,
        easing = LinearEasing,
    )
)

fun slideInFromTop() = slideInVertically(initialOffsetY = { it / 2 }) + fadeIn(
    animationSpec = tween(
        durationMillis = ENTER_ANIM_MILLIS,
        easing = LinearEasing,
    )
)
