package ke.co.tulivuapps.hoteltours.domain.usecase.favorite

import ke.co.tulivuapps.hoteltours.data.model.dto.CharacterDto
import ke.co.tulivuapps.hoteltours.data.model.dto.extension.toFavoriteDtoList
import ke.co.tulivuapps.hoteltours.domain.base.BaseUseCase
import ke.co.tulivuapps.hoteltours.domain.base.IParams
import ke.co.tulivuapps.hoteltours.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.flow

/**
 * Created by Rushi on 30.03.2023
 */

class GetFavoritesUseCase(
    internal val repository: CharacterRepository
) : BaseUseCase<IParams, List<CharacterDto>> {

    override suspend fun invoke(param: IParams) = flow {
        val favorites = repository.getFavoriteList()
        emit(favorites.toFavoriteDtoList())
    }
}