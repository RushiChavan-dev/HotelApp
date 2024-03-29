package ke.co.tulivuapps.hoteltours.features.component

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.navOptions
import androidx.tracing.trace
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import ke.co.tulivuapps.hoteltours.R
import ke.co.tulivuapps.hoteltours.features.navigation.BottomNav
import ke.co.tulivuapps.hoteltours.features.screen.booking.navigation.navigateToBookings
import ke.co.tulivuapps.hoteltours.features.screen.favorites.navigation.navigateToFavorites
import ke.co.tulivuapps.hoteltours.features.screen.homee.navigation.navigateToHomee
import ke.co.tulivuapps.hoteltours.features.screen.popular.navigation.navigateToPopular
import ke.co.tulivuapps.hoteltours.features.screen.profile.navigation.navigateToProfile


/**
 * Created by Rushi on 12.03.2023
 */

@Composable
fun HobbyHorseToursBottomAppBar(
    navController: NavController,
    currentDestination: NavDestination?,
) {
    BottomAppBar(
        modifier = Modifier
            .clip(
                RoundedCornerShape(
                    topStart = 30.dp,
                    topEnd = 30.dp
                )
            ),
        cutoutShape = CircleShape,
        elevation = 10.dp,
        backgroundColor = MaterialTheme.colors.onSecondary
    ) {
        BottomNav.values().forEach { screen ->
            val selected = currentDestination.isBottomNavDestinationInHierarchy(screen)
            val primaryColor = MaterialTheme.colors.primary
            val secondaryColor = MaterialTheme.colors.secondary

            val painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(data = screen.iconId)
                    .apply(block = fun ImageRequest.Builder.() {
                            crossfade(false)
                            size(Size(1200, 900))
                            error(R.drawable.coffee)
                            fallback(R.drawable.coffee)
                        }).build()
            )

            BottomNavigationItem(
                alwaysShowLabel = true,
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = MaterialTheme.colors.secondary,
                icon = {
                    Icon(
                        painter = painter,// ImageVector.vectorResource(id = screen.iconId),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    HobbyHorseToursText(
                        text = if (screen.titleTextId == R.string.favorite_screen_title) "" else stringResource(
                            id = screen.titleTextId
                        ),
                        color = if (selected) primaryColor else secondaryColor,
                        style = MaterialTheme.typography.overline,
                        textAlign = TextAlign.Center,
                        maxLines = 1
                    )
                },
                selected = selected,
                onClick = {
                    navigateToBottomNavDestination(screen, navController)
                }
            )
        }
    }
}

fun navigateToBottomNavDestination(bottomNav: BottomNav, navController: NavController) {
    trace("Navigation: ${bottomNav.name}") {
        val bottomNavOptions = navOptions {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }

        when (bottomNav) {
            BottomNav.HOMEE -> navController.navigateToHomee(bottomNavOptions)
            BottomNav.POPULAR -> navController.navigateToPopular(bottomNavOptions)
            BottomNav.FAVORITES -> navController.navigateToFavorites(bottomNavOptions)
            BottomNav.BOOKING -> navController.navigateToBookings(bottomNavOptions)
            BottomNav.PROFILE -> navController.navigateToProfile(bottomNavOptions)
        }
    }
}

private fun NavDestination?.isBottomNavDestinationInHierarchy(destination: BottomNav) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false
