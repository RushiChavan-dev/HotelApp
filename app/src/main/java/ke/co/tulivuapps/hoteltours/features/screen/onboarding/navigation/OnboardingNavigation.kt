@file:OptIn(ExperimentalAnimationApi::class)

package ke.co.tulivuapps.hoteltours.features.screen.onboarding.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import ke.co.tulivuapps.hoteltours.features.screen.homee.navigation.navigateToHomee
import ke.co.tulivuapps.hoteltours.features.screen.onboarding.OnBoardingScreen

/**
 * Created by Rushi on 23.01.2023
 */

const val onboardingNavigationRoute = "onboarding_route"

fun NavController.navigateToOnboarding(
    navOptions: NavOptions? = null
) {
    this.navigate(onboardingNavigationRoute, navOptions)
}


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.onbooardingScreen(navController: NavHostController) {
    composable(onboardingNavigationRoute,
        content={
            OnBoardingScreen(onSkip = {navController.navigateToHomee()},
                navController = navController)
                },
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Right,
                animationSpec = tween(700)
            )
        })
}