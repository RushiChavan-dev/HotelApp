package ke.co.tulivuapps.hoteltours.domain.viewstate.profile

import androidx.compose.runtime.Stable
import ke.co.tulivuapps.hoteltours.data.model.popular.PopularResponse
import ke.co.tulivuapps.hoteltours.domain.viewstate.IViewState

/**
 * Created by Rushi on 19.03.2023
 */

@Stable
data class



ProfileViewState(
    val isLoading: Boolean = false,
    val data: List<PopularResponse>? = null
) : IViewState