package ke.co.tulivuapps.hoteltours.domain.viewstate.popular

import androidx.compose.runtime.Stable
import ke.co.tulivuapps.hoteltours.data.model.popular.ResultPopular
import ke.co.tulivuapps.hoteltours.domain.viewstate.IViewState

/**
 * Created by Rushi on 19.03.2023
 */

@Stable
data class



PopularViewState(
    val isLoading: Boolean = false,
    val data: List<ResultPopular>? = null
) : IViewState