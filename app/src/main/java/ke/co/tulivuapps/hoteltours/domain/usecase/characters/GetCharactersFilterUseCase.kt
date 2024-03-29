package ke.co.tulivuapps.hoteltours.domain.usecase.characters

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ke.co.tulivuapps.hoteltours.data.model.dto.CharacterDto
import ke.co.tulivuapps.hoteltours.domain.base.BaseUseCase
import ke.co.tulivuapps.hoteltours.domain.base.IParams
import ke.co.tulivuapps.hoteltours.domain.repository.CharacterRepository
import ke.co.tulivuapps.hoteltours.domain.usecase.hotels.GetHotelsFilterUseCase
import ke.co.tulivuapps.hoteltours.domain.usecase.hotels.HotelsFilterPagingSource
import kotlinx.coroutines.flow.Flow

/**
 * Created by Rushi on 12.04.2023
 */

class GetCharactersFilterUseCase(
    internal val repository: CharacterRepository
) : BaseUseCase<GetCharactersFilterUseCase.Params, PagingData<CharacterDto>> {

    data class Params(
        val pagingConfig: PagingConfig,
        val options: Map<String, String>
    ) : IParams

    override suspend fun invoke(param: Params): Flow<PagingData<CharacterDto>> {
        return Pager(
            config = param.pagingConfig,
            pagingSourceFactory = { CharactersFilterPagingSource(repository, param.options) }
        ).flow
    }
}