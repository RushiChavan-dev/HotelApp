@file:OptIn(ExperimentalAnimationApi::class)

package ke.co.tulivuapps.hoteltours.features.screen.search.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import ke.co.tulivuapps.hoteltours.features.screen.destinationsdetail.navigation.navigateDestinationsDetail
import ke.co.tulivuapps.hoteltours.features.screen.hotels.navigation.navigateHotelsDetail
import ke.co.tulivuapps.hoteltours.features.screen.search.SearchScreen
import ke.co.tulivuapps.hoteltours.utils.Utility.toJson

/**
 * Created by Rushi on 23.01.2023
 */

const val searchNavigationRoute = "search_route"

fun NavController.navigateToSearch(travelstyle: String,city: String, navOptions: NavOptions? = null) {
    this.navigate(searchNavigationRoute.plus("?travelstyle=${travelstyle}&city=${city}"), navOptions)
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.searchScreen(navController: NavHostController) {
    composable(searchNavigationRoute.plus("?travelstyle={travelstyle}&city={city}"),content={
        SearchScreen(
            hiltViewModel(),
            navigateToHotelDto = {
                navController.navigateHotelsDetail(it.toJson())
            }
            ,navigateToBack = {
                navController.navigateUp()
            }
            ,
            navigateToDestinationDto = {
                navController.navigateDestinationsDetail(it.toJson())
            }
        )
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