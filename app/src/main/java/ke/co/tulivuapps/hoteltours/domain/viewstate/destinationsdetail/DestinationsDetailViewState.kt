package ke.co.tulivuapps.hoteltours.domain.viewstate.destinationsdetail

import androidx.compose.runtime.Stable
import ke.co.tulivuapps.hoteltours.data.model.Result
import ke.co.tulivuapps.hoteltours.domain.viewstate.IViewState

/**
 * Created by Rushi on 13.03.2023
 */
@Stable
data class DestinationsDetailViewState(
    val isLoading: Boolean = false,
    val data: Result? = null
) : IViewState