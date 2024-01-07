package ke.co.tulivuapps.hoteltours.domain.viewstate.city

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import ke.co.tulivuapps.hoteltours.data.model.Result
import ke.co.tulivuapps.hoteltours.data.model.dto.CityDto
import ke.co.tulivuapps.hoteltours.domain.viewstate.IViewState
import kotlinx.coroutines.flow.Flow

/**
 * Created by Rushi on 13.03.2023
 */
@Stable
data class CityViewState(
    val isLoading: Boolean = false,
    val pagedData: Flow<PagingData<CityDto>>? = null,
    val data: List<Result>? = null,
) : IViewState