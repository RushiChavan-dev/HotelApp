package ke.co.tulivuapps.hoteltours.domain.usecase.destinations

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ke.co.tulivuapps.hoteltours.data.model.dto.CharacterDto
import ke.co.tulivuapps.hoteltours.data.model.dto.DestinationDto
import ke.co.tulivuapps.hoteltours.domain.base.BaseUseCase
import ke.co.tulivuapps.hoteltours.domain.base.IParams
import ke.co.tulivuapps.hoteltours.domain.repository.DestinationRepository
import ke.co.tulivuapps.hoteltours.domain.usecase.hotels.GetHotelsUseCase
import kotlinx.coroutines.flow.Flow

/**
 * Created by Rushi on 27.03.2023
 */

class GetDestinationsUseCase(
    internal val repository: DestinationRepository
) : BaseUseCase<GetDestinationsUseCase.Params, PagingData<DestinationDto>> {

    data class Params(
        val pagingConfig: PagingConfig,
        val options: Map<String, String>
    ) : IParams

    override suspend fun invoke(param: Params): Flow<PagingData<DestinationDto>> {
        return Pager(
            config = param.pagingConfig,
            pagingSourceFactory = { DestinationPagingSource(repository, param.options) }
        ).flow
    }
}