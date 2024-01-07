package ke.co.tulivuapps.hoteltours.domain.usecase.favorite

import ke.co.tulivuapps.hoteltours.data.model.dto.CharacterDto
import ke.co.tulivuapps.hoteltours.data.model.dto.extension.toFavoriteEntity
import ke.co.tulivuapps.hoteltours.domain.base.BaseUseCase
import ke.co.tulivuapps.hoteltours.domain.base.IParams
import ke.co.tulivuapps.hoteltours.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.flow

/**
 * Created by Rushi on 27.03.2023
 */

class UpdateFavoriteUseCase(
    internal val repository: CharacterRepository
) : BaseUseCase<UpdateFavoriteUseCase.Params, Unit> {

    data class Params(
        val character: CharacterDto
    ) : IParams

    override suspend fun invoke(param: Params) = flow<Unit> {
        val dto = param.character
        val character = repository.getFavorite(dto.id ?: 0)
        if (character == null) {
            repository.saveFavorite(dto.toFavoriteEntity())
        } else {
            repository.deleteFavoriteById(dto.id ?: 0)
        }
        emit(Unit)
    }
}