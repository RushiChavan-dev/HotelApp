package ke.co.tulivuapps.hoteltours.domain.usecase.booknow

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ke.co.tulivuapps.hoteltours.data.model.dto.BookingDto
import ke.co.tulivuapps.hoteltours.domain.base.BaseUseCase
import ke.co.tulivuapps.hoteltours.domain.base.IParams
import ke.co.tulivuapps.hoteltours.domain.repository.BookingsRepository
import ke.co.tulivuapps.hoteltours.domain.usecase.bookings.BookingsPagingSource
import kotlinx.coroutines.flow.Flow

/**
 * Created by Rushi on 27.03.2023
 */

class GetBookNowUseCase(
    internal val repository: BookingsRepository
) : BaseUseCase<GetBookNowUseCase.Params, PagingData<BookingDto>> {

    data class Params(
        val pagingConfig: PagingConfig,
        val options: Map<String, String>
    ) : IParams

    override suspend fun invoke(param: Params): Flow<PagingData<BookingDto>> {
        return Pager(
            config = param.pagingConfig,
            pagingSourceFactory = { BookingsPagingSource(repository, param.options) }
        ).flow
    }
}