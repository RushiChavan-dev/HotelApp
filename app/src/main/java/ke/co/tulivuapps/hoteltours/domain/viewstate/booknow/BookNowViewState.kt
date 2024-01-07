package ke.co.tulivuapps.hoteltours.domain.viewstate.booknow

import androidx.compose.runtime.Stable
import ke.co.tulivuapps.hoteltours.data.model.Result
import ke.co.tulivuapps.hoteltours.data.model.booking.BookingInfoResponse
import ke.co.tulivuapps.hoteltours.domain.viewstate.IViewState

/**
 * Created by Rushi on 13.03.2023
 */
@Stable
data class BookNowViewState(
    val isLoading: Boolean = false,
    val data: Result? = null,
    val bookingResponse: BookingInfoResponse? = null,
) : IViewState