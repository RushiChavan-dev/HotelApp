package ke.co.tulivuapps.hoteltours.data.remote.source

import ke.co.tulivuapps.hoteltours.data.model.city.CityFavoriteEntity
import ke.co.tulivuapps.hoteltours.data.model.city.CityInfoResponse
import ke.co.tulivuapps.hoteltours.data.model.city.CityResponse
import ke.co.tulivuapps.hoteltours.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

/**
 * Created by Rushi on 12.03.2023
 */

interface CityRemoteDataSource {
    suspend fun getAllCities(page: Int, options: Map<String, String>): Response<CityResponse>
    suspend fun getFilterCities(page: Int, options: Map<String, String>): Response<CityResponse>
    suspend fun getCity(cityId: Int): Flow<DataState<CityInfoResponse>>
    suspend fun getCity(url: String): Flow<DataState<CityInfoResponse>>
    suspend fun getFavoriteList(): List<CityFavoriteEntity>
    suspend fun getFavorite(favoriteId: Int): CityFavoriteEntity? = null
    suspend fun deleteFavoriteById(favoriteId: Int)
    suspend fun deleteFavoriteList()
    suspend fun saveFavorite(entity: CityFavoriteEntity)
    suspend fun saveFavoriteList(entityList: List<CityFavoriteEntity>)

}