package ke.co.tulivuapps.hoteltours.domain.repository

import ke.co.tulivuapps.hoteltours.data.model.city.CityFavoriteEntity
import ke.co.tulivuapps.hoteltours.data.model.city.CityInfoResponse
import ke.co.tulivuapps.hoteltours.data.model.city.CityResponse
import ke.co.tulivuapps.hoteltours.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

/**
 * Created by Rushi on 12.03.2023
 */

interface CityRepository {
    suspend fun getAllCities(page: Int, options: Map<String, String>): Response<CityResponse>
    fun getCity(characterId: Int): Flow<DataState<CityInfoResponse>>
    fun getCity(url: String): Flow<DataState<CityInfoResponse>>
    suspend fun getFilterCity(page: Int, options: Map<String, String>): Response<CityResponse>
    suspend fun getCityFavoriteList(): List<CityFavoriteEntity>
    suspend fun getFavoriteCity(favoriteId: Int): CityFavoriteEntity? = null
    suspend fun deleteFavoriteCityById(favoriteId: Int)
    suspend fun deleteFavoriteCityList()
    suspend fun saveFavoriteCity(entity: CityFavoriteEntity)
    suspend fun saveFavoriteCityList(entityList: List<CityFavoriteEntity>)
}