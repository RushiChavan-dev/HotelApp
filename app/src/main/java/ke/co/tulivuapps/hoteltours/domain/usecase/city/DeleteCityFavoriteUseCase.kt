package ke.co.tulivuapps.hoteltours.domain.usecase.city

import ke.co.tulivuapps.hoteltours.domain.base.BaseUseCase
import ke.co.tulivuapps.hoteltours.domain.base.IParams
import ke.co.tulivuapps.hoteltours.domain.repository.CityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Rushi on 29.03.2023
 */

class DeleteCityFavoriteUseCase(
    internal val repository: CityRepository
) : BaseUseCase<DeleteCityFavoriteUseCase.Params, Unit> {

    data class Params(
        val cityId: Int?
    ) : IParams

    override suspend fun invoke(param: Params): Flow<Unit> {
        param.cityId?.let {
            repository.deleteFavoriteCityById(param.cityId)
        } ?: kotlin.run {
            repository.deleteFavoriteCityList()
        }
        return flow { emit(Unit) }
    }
}