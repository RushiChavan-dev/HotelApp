package ke.co.tulivuapps.hoteltours.features.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import ke.co.tulivuapps.hoteltours.features.calendar.navigation.calendarScreen
import ke.co.tulivuapps.hoteltours.features.component.HobbyHorseToursBottomAppBar
import ke.co.tulivuapps.hoteltours.features.component.HobbyHorseToursFloatingActionBar
import ke.co.tulivuapps.hoteltours.features.component.HobbyHorseToursScaffold
import ke.co.tulivuapps.hoteltours.features.screen.booking.navigation.bookingsScreen
import ke.co.tulivuapps.hoteltours.features.screen.bookingdetail.navigation.bookingDetailScreen
import ke.co.tulivuapps.hoteltours.features.screen.booknow.navigation.bookNowScreen
import ke.co.tulivuapps.hoteltours.features.screen.charactersdetail.navigation.charactersDetailScreen
import ke.co.tulivuapps.hoteltours.features.screen.charactersdetail.navigation.navigateCharactersDetail
import ke.co.tulivuapps.hoteltours.features.screen.cities.navigation.citiesScreen
import ke.co.tulivuapps.hoteltours.features.screen.destinations.navigation.destinationScreen
import ke.co.tulivuapps.hoteltours.features.screen.destinationsdetail.navigation.destinationsDetailScreen
import ke.co.tulivuapps.hoteltours.features.screen.favorites.navigation.favoritesScreen
import ke.co.tulivuapps.hoteltours.features.screen.home.navigation.homesScreen
import ke.co.tulivuapps.hoteltours.features.screen.homee.navigation.homeeNavigationRoute
import ke.co.tulivuapps.hoteltours.features.screen.homee.navigation.homeesScreen
import ke.co.tulivuapps.hoteltours.features.screen.hotels.navigation.hotelsScreen
import ke.co.tulivuapps.hoteltours.features.screen.hotelsdetail.navigation.hotelsDetailScreen
import ke.co.tulivuapps.hoteltours.features.screen.login.navigation.loginScreen
import ke.co.tulivuapps.hoteltours.features.screen.onboarding.navigation.onboardingNavigationRoute
import ke.co.tulivuapps.hoteltours.features.screen.onboarding.navigation.onbooardingScreen
import ke.co.tulivuapps.hoteltours.features.screen.popular.navigation.episodesScreen
import ke.co.tulivuapps.hoteltours.features.screen.profile.navigation.profileScreen
import ke.co.tulivuapps.hoteltours.features.screen.search.navigation.searchScreen
import ke.co.tulivuapps.hoteltours.features.screen.settings.navigation.settingsScreen
import ke.co.tulivuapps.hoteltours.features.screen.signup.navigation.signUpScreen
import ke.co.tulivuapps.hoteltours.features.screen.travelstyles.navigation.travelStylesScreen
import ke.co.tulivuapps.hoteltours.utils.Utility.toJson

/**
 * Created by Rushi on 9.03.2023
 */

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph(isOnBoarded: Boolean) {
    val scaffoldState = rememberScaffoldState()
    val navController = rememberAnimatedNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    HobbyHorseToursScaffold(
        scaffoldState= scaffoldState,
        bottomBar = {
            BottomNav.values().forEach { navItem ->
                if (navItem.route == currentRoute) {
                    HobbyHorseToursBottomAppBar(
                        navController = navController,
                        currentDestination = currentDestination
                    )
                }
            }
        },
        floatingActionButton = {
            BottomNav.values().forEach { navItem ->
                if (navItem.route == currentRoute) {
                    HobbyHorseToursFloatingActionBar( navController = navController, )
                }
            }
        },
        backgroundColor = MaterialTheme.colors.background,
    ) { innerPadding ->
        AnimatedNavHost(
            navController = navController,
            startDestination = if (isOnBoarded)  homeeNavigationRoute else onboardingNavigationRoute,// signUpNavigationRoute,//if (isOnBoarded)  homeeNavigationRoute else onboardingNavigationRoute,
            Modifier.padding(innerPadding)
        ) {
            onbooardingScreen(navController)
            homeesScreen(navController)
            homesScreen(navController)
            loginScreen(navController)
            signUpScreen(navController)
            calendarScreen(navController)
            destinationScreen(navController)
            destinationsDetailScreen { navController.navigateUp() }
            citiesScreen(navController)
            hotelsScreen(navController)
            hotelsDetailScreen(navController)
            travelStylesScreen(navController)
            charactersDetailScreen { navController.navigateUp() }
            bookingsScreen(navController)
            bookNowScreen(navController)
            bookingDetailScreen(navController)
            episodesScreen(navController)
            searchScreen(navController)
            profileScreen(navController)
            settingsScreen()
            favoritesScreen { navController.navigateCharactersDetail(it.toJson()) }
        }
    }
}


