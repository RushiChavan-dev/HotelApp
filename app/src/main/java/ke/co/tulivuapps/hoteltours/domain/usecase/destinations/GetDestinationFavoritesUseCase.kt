package ke.co.tulivuapps.hoteltours.domain.usecase.destinations

import ke.co.tulivuapps.hoteltours.data.model.dto.CharacterDto
import ke.co.tulivuapps.hoteltours.data.model.dto.DestinationDto
import ke.co.tulivuapps.hoteltours.data.model.dto.extension.toDestinationFavoriteDtoList
import ke.co.tulivuapps.hoteltours.data.model.dto.extension.toFavoriteDtoList
import ke.co.tulivuapps.hoteltours.domain.base.BaseUseCase
import ke.co.tulivuapps.hoteltours.domain.base.IParams
import ke.co.tulivuapps.hoteltours.domain.repository.CharacterRepository
import ke.co.tulivuapps.hoteltours.domain.repository.DestinationRepository
import kotlinx.coroutines.flow.flow

/**
 * Created by Rushi on 30.03.2023
 */

class GetDestinationFavoritesUseCase(
    internal val repository: DestinationRepository
) : BaseUseCase<IParams, List<DestinationDto>> {

    override suspend fun invoke(param: IParams) = flow {
        val favorites = repository.getFavoriteList()
        emit(favorites.toDestinationFavoriteDtoList())
    }
}