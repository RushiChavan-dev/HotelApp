package ke.co.tulivuapps.hoteltours.domain.viewstate.home

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import com.google.common.collect.ImmutableList
import ke.co.tulivuapps.hoteltours.data.model.Result
import ke.co.tulivuapps.hoteltours.data.model.dto.CityDto
import ke.co.tulivuapps.hoteltours.data.model.dto.DestinationDto
import ke.co.tulivuapps.hoteltours.data.model.dto.HotelDto
import ke.co.tulivuapps.hoteltours.data.model.dto.TravelStyleDto
import ke.co.tulivuapps.hoteltours.domain.viewstate.IViewState
import kotlinx.coroutines.flow.Flow

/**
 * Created by Rushi on 13.03.2023
 */
@Stable
data class HomeViewState(
    val isLoading: Boolean = false,
    val pagedCityData: Flow<PagingData<CityDto>>? = null,
    val pagedTravelStyleData: Flow<PagingData<TravelStyleDto>>? = null,
    val pagedDestinationData: Flow<PagingData<DestinationDto>>? = null,
    val pagedHotelData: Flow<PagingData<HotelDto>>? = null,
    val data: ImmutableList<Result>? = null,
) : IViewState