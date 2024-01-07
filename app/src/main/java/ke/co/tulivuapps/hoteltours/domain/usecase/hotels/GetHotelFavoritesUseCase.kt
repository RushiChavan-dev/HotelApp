package ke.co.tulivuapps.hoteltours.domain.usecase.hotels

import ke.co.tulivuapps.hoteltours.data.model.dto.HotelDto
import ke.co.tulivuapps.hoteltours.data.model.dto.extension.toHotelFavoriteDtoList
import ke.co.tulivuapps.hoteltours.domain.base.BaseUseCase
import ke.co.tulivuapps.hoteltours.domain.base.IParams
import ke.co.tulivuapps.hoteltours.domain.repository.HotelRepository
import kotlinx.coroutines.flow.flow

/**
 * Created by Rushi on 30.03.2023
 */

class GetHotelFavoritesUseCase(
    internal val repository: HotelRepository
) : BaseUseCase<IParams, List<HotelDto>> {

    override suspend fun invoke(param: IParams) = flow {
        val favorites = repository.getFavoriteList()
        emit(favorites.toHotelFavoriteDtoList())
    }
}