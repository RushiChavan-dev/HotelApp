package ke.co.tulivuapps.hoteltours.domain.viewstate.favorites

import androidx.compose.runtime.Stable
import ke.co.tulivuapps.hoteltours.data.model.FavoriteEntity
import ke.co.tulivuapps.hoteltours.domain.viewstate.IViewState

/**
 * Created by Rushi on 30.03.2023
 */

@Stable
data class FavoritesViewState(
    val favoritesList: List<FavoriteEntity> = emptyList(),
    val favoriteId: Int? = null,
    val isDisplay: Boolean = false,
    val isAllDeleteFavorites: Boolean = false,
    val isLoading: Boolean = false,
) : IViewState