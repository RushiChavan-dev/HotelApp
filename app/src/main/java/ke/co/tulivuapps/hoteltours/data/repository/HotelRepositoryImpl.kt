package ke.co.tulivuapps.hoteltours.data.repository

import ke.co.tulivuapps.hoteltours.data.local.dao.HotelDao
import ke.co.tulivuapps.hoteltours.data.model.hotel.HotelFavoriteEntity
import ke.co.tulivuapps.hoteltours.data.model.hotel.HotelInfoResponse
import ke.co.tulivuapps.hoteltours.data.model.hotel.HotelResponse
import ke.co.tulivuapps.hoteltours.data.remote.source.HotelRemoteDataSource
import ke.co.tulivuapps.hoteltours.data.remote.utils.DataState
import ke.co.tulivuapps.hoteltours.domain.repository.HotelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Rushi on 12.03.2023
 */

class HotelRepositoryImpl @Inject constructor(
    private val characterRemoteDataSource: HotelRemoteDataSource,
    private val dao: HotelDao
) : HotelRepository {

    override suspend fun getAllHotels(
        page: Int,
        options: Map<String, String>
    ): Response<HotelResponse> {
        return characterRemoteDataSource.getAllHotels(page = page, options = options)
    }

    override fun getHotel(characterId: Int): Flow<DataState<HotelInfoResponse>> = flow {
        emitAll(characterRemoteDataSource.getHotel(characterId = characterId))
    }

    override fun getHotel(url: String): Flow<DataState<HotelInfoResponse>> = flow {
        emitAll(characterRemoteDataSource.getHotel(url = url))
    }

    override suspend fun getFilterHotels(
        page: Int,
        options: Map<String, String>
    ): Response<HotelResponse> = characterRemoteDataSource.getFilterHotels(page, options)

    override suspend fun getFavorite(favoriteId: Int): HotelFavoriteEntity? = dao.getFavorite(favoriteId)

    override suspend fun getFavoriteList(): List<HotelFavoriteEntity> = dao.getFavoriteList()

    override suspend fun deleteFavoriteById(favoriteId: Int) = dao.deleteFavoriteById(favoriteId)

    override suspend fun deleteFavoriteList() = dao.deleteFavoriteList()

    override suspend fun saveFavorite(entity: HotelFavoriteEntity) = dao.insert(entity)

    override suspend fun saveFavoriteList(entityList: List<HotelFavoriteEntity>) = dao.insert(entityList)
}