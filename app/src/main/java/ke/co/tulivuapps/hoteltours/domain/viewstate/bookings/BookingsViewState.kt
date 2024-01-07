package ke.co.tulivuapps.hoteltours.domain.viewstate.bookings

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import ke.co.tulivuapps.hoteltours.data.model.booking.ResultBooking
import ke.co.tulivuapps.hoteltours.data.model.dto.BookingDto
import ke.co.tulivuapps.hoteltours.domain.viewstate.IViewState
import kotlinx.coroutines.flow.Flow

/**
 * Created by Rushi on 13.03.2023
 */
@Stable
data class BookingsViewState(
    val isLoading: Boolean = false,
    val pagedData: Flow<PagingData<BookingDto>>? = null,
    val data: List<ResultBooking>? = null,
) : IViewState