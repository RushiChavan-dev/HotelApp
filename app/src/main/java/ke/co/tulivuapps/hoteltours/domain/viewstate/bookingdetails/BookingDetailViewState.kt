package ke.co.tulivuapps.hoteltours.domain.viewstate.bookingdetails

import androidx.compose.runtime.Stable
import ke.co.tulivuapps.hoteltours.data.model.booking.ResultBooking
import ke.co.tulivuapps.hoteltours.domain.viewstate.IViewState

/**
 * Created by Rushi on 13.03.2023
 */
@Stable
data class BookingDetailViewState(
    val isLoading: Boolean = false,
    val data: ResultBooking? = null
) : IViewState