package ke.co.tulivuapps.hoteltours.domain.viewstate.travelstyle

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import ke.co.tulivuapps.hoteltours.data.model.Result
import ke.co.tulivuapps.hoteltours.data.model.dto.TravelStyleDto
import ke.co.tulivuapps.hoteltours.domain.viewstate.IViewState
import kotlinx.coroutines.flow.Flow

/**
 * Created by Rushi on 13.03.2023
 */
@Stable
data class TravelStyleViewState(
    val isLoading: Boolean = false,
    val pagedData: Flow<PagingData<TravelStyleDto>>? = null,
    val data: List<Result>? = null,
) : IViewState