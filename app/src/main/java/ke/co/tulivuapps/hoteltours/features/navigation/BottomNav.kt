package ke.co.tulivuapps.hoteltours.features.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ke.co.tulivuapps.hoteltours.R
import ke.co.tulivuapps.hoteltours.features.screen.booking.navigation.bookingsNavigationRoute
import ke.co.tulivuapps.hoteltours.features.screen.favorites.navigation.favoritesNavigationRoute
import ke.co.tulivuapps.hoteltours.features.screen.homee.navigation.homeeNavigationRoute
import ke.co.tulivuapps.hoteltours.features.screen.popular.navigation.popularNavigationRoute
import ke.co.tulivuapps.hoteltours.features.screen.profile.navigation.profileNavigationRoute

/**
 * Created by Rushi on 9.03.2023
 */

enum class BottomNav(
    val route: String,
    @DrawableRes val iconId: Int,
    @StringRes val titleTextId: Int
) {
//    HOME(
//        homeNavigationRoute,
//        R.drawable.ic_outline_people,
//        R.string.home_screen_title,
//    ),
    HOMEE(
        homeeNavigationRoute,
        R.drawable.home,
        R.string.home_screen_title,
    ),
    POPULAR(
        popularNavigationRoute,
        R.drawable.fire,
        R.string.popular_screen_title
    ),
    FAVORITES(
        favoritesNavigationRoute,
        R.drawable.ic_baseline_favorite_24,
        R.string.favorite_screen_title,
    ),
    BOOKING(
        bookingsNavigationRoute,
        R.drawable.appointment,
        R.string.booking_screen_title,
    ),
    PROFILE(
        profileNavigationRoute,
        R.drawable.ic_person,
        R.string.profile_screen_title
    )
}