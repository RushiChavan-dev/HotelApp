package ke.co.tulivuapps.hoteltours.domain.usecase.destinations

import ke.co.tulivuapps.hoteltours.data.model.dto.DestinationDto
import ke.co.tulivuapps.hoteltours.data.model.dto.extension.toDestinationFavoriteEntity
import ke.co.tulivuapps.hoteltours.domain.base.BaseUseCase
import ke.co.tulivuapps.hoteltours.domain.base.IParams
import ke.co.tulivuapps.hoteltours.domain.repository.DestinationRepository
import kotlinx.coroutines.flow.flow

/**
 * Created by Rushi on 27.03.2023
 */

class UpdateDestinationFavoriteUseCase(
    internal val repository: DestinationRepository
) : BaseUseCase<UpdateDestinationFavoriteUseCase.Params, Unit> {

    data class Params(
        val character: DestinationDto
    ) : IParams

    override suspend fun invoke(param: Params) = flow<Unit> {
        val dto = param.character
        val character = repository.getFavorite(dto.localId ?: 0)
        if (character == null) {
            repository.saveFavorite(dto.toDestinationFavoriteEntity())
        } else {
            repository.deleteFavoriteById(dto.localId ?: 0)
        }
        emit(Unit)
    }
}