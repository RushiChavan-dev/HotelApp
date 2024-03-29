@file:OptIn(ExperimentalAnimationApi::class)

package ke.co.tulivuapps.hoteltours.features.screen.destinations.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import ke.co.tulivuapps.hoteltours.features.screen.destinations.DestinationsScreen
import ke.co.tulivuapps.hoteltours.features.screen.destinationsdetail.navigation.navigateDestinationsDetail
import ke.co.tulivuapps.hoteltours.utils.Utility.toJson

/**
 * Created by Rushi on 23.01.2023
 */

const val destinationsNavigationRoute = "destinations_route"

fun NavController.navigateToDestinations(
    navOptions: NavOptions? = null
) {
    this.navigate(destinationsNavigationRoute, navOptions)
}

fun NavGraphBuilder.destinationScreen(navController: NavHostController) {
    composable(destinationsNavigationRoute,
        content = { DestinationsScreen(viewModel = hiltViewModel(),
                                        navigateToDetail = {navController.navigateDestinationsDetail(it.toJson())}  ) },
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
