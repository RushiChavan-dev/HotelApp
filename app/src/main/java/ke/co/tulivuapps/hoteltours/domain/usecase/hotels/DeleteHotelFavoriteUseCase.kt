package ke.co.tulivuapps.hoteltours.domain.usecase.hotels

import ke.co.tulivuapps.hoteltours.domain.base.BaseUseCase
import ke.co.tulivuapps.hoteltours.domain.base.IParams
import ke.co.tulivuapps.hoteltours.domain.repository.CharacterRepository
import ke.co.tulivuapps.hoteltours.domain.repository.HotelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Rushi on 29.03.2023
 */

class DeleteHotelFavoriteUseCase(
    internal val repository: HotelRepository
) : BaseUseCase<DeleteHotelFavoriteUseCase.Params, Unit> {

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