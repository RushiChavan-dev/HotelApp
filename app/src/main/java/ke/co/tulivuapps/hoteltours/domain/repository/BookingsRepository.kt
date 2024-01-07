package ke.co.tulivuapps.hoteltours.domain.repository

import ke.co.tulivuapps.hoteltours.data.model.booking.BookingInfoResponse
import ke.co.tulivuapps.hoteltours.data.model.booking.BookingResponse
import ke.co.tulivuapps.hoteltours.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

/**
 * Created by Rushi on 12.03.2023
 */

interface BookingsRepository {
    suspend fun getAllBookings(page: Int, options: Map<String, String>): Response<BookingResponse>
    fun getBooking(characterId: Int): Flow<DataState<BookingInfoResponse>>
    fun getBooking(url: String): Flow<DataState<BookingInfoResponse>>
    suspend fun getFilterBooking(page: Int, options: Map<String, String>): Response<BookingResponse>
    suspend fun bookHotel(bookingInfoResponse: BookingInfoResponse): Flow<DataState<BookingInfoResponse>>
}