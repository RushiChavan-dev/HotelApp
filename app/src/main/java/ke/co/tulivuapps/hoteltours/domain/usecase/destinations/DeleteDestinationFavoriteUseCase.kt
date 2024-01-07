package ke.co.tulivuapps.hoteltours.domain.usecase.destinations

import ke.co.tulivuapps.hoteltours.domain.base.BaseUseCase
import ke.co.tulivuapps.hoteltours.domain.base.IParams
import ke.co.tulivuapps.hoteltours.domain.repository.CharacterRepository
import ke.co.tulivuapps.hoteltours.domain.repository.DestinationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Rushi on 29.03.2023
 */

class DeleteDestinationFavoriteUseCase(
    internal val repository: DestinationRepository
) : BaseUseCase<DeleteDestinationFavoriteUseCase.Params, Unit> {

    data class Params(
        val characterId: Int?
    ) : IParams

    override suspend fun invoke(param: Params): Flow<Unit> {
        param.characterId?.let {
            repository.deleteFavoriteById(param.characterId)
        } ?: kotlin.run {
            repository.deleteFavoriteList()
        }
        return flow { emit(Unit) }
    }
}