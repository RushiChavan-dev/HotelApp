package ke.co.tulivuapps.hoteltours.domain.usecase.bookings

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ke.co.tulivuapps.hoteltours.data.model.dto.BookingDto
import ke.co.tulivuapps.hoteltours.domain.base.BaseUseCase
import ke.co.tulivuapps.hoteltours.domain.base.IParams
import ke.co.tulivuapps.hoteltours.domain.repository.BookingsRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by Rushi on 12.04.2023
 */

class GetBookingsFilterUseCase(
    internal val repository: BookingsRepository
) : BaseUseCase<GetBookingsFilterUseCase.Params, PagingData<BookingDto>> {

    data class Params(
        val pagingConfig: PagingConfig,
        val options: Map<String, String>
    ) : IParams

    override suspend fun invoke(param: Params): Flow<PagingData<BookingDto>> {
        return Pager(
            config = param.pagingConfig,
            pagingSourceFactory = { BookingsFilterPagingSource(repository, param.options) }
        ).flow
    }
}