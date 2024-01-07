package ke.co.tulivuapps.hoteltours.domain.usecase.travelstyle

import ke.co.tulivuapps.hoteltours.domain.base.BaseUseCase
import ke.co.tulivuapps.hoteltours.domain.base.IParams
import ke.co.tulivuapps.hoteltours.domain.repository.TravelStyleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Rushi on 29.03.2023
 */

class DeleteTravelStyleFavoriteUseCase(
    internal val repository: TravelStyleRepository
) : BaseUseCase<DeleteTravelStyleFavoriteUseCase.Params, Unit> {

    data class Params(
        val travelStyleId: Int?
    ) : IParams

    override suspend fun invoke(param: Params): Flow<Unit> {
        param.travelStyleId?.let {
            repository.deleteFavoriteTravelStyleById(param.travelStyleId)
        } ?: kotlin.run {
            repository.deleteFavoriteTravelStyleList()
        }
        return flow { emit(Unit) }
    }
}