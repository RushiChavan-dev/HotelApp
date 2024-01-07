package ke.co.tulivuapps.hoteltours.domain.usecase.hotels

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ke.co.tulivuapps.hoteltours.data.model.dto.HotelDto
import ke.co.tulivuapps.hoteltours.domain.base.BaseUseCase
import ke.co.tulivuapps.hoteltours.domain.base.IParams
import ke.co.tulivuapps.hoteltours.domain.repository.HotelRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by Rushi on 27.03.2023
 */

class GetHotelsUseCase(
    internal val repository: HotelRepository
) : BaseUseCase<GetHotelsUseCase.Params, PagingData<HotelDto>> {

    data class Params(
        val pagingConfig: PagingConfig,
        val options: Map<String, String>
    ) : IParams

    override suspend fun invoke(param: Params): Flow<PagingData<HotelDto>> {
        return Pager(
            config = param.pagingConfig,
            pagingSourceFactory = { HotelPagingSource(repository, param.options) }
        ).flow
    }
}