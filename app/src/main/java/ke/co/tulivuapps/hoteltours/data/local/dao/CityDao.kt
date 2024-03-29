package ke.co.tulivuapps.hoteltours.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import ke.co.tulivuapps.hoteltours.data.local.base.BaseDao
import ke.co.tulivuapps.hoteltours.data.model.city.CityFavoriteEntity
import ke.co.tulivuapps.hoteltours.data.remote.utils.Constants

/**
 * Created by Rushi on 27.03.2023
 */

@Dao
interface CityDao : BaseDao<CityFavoriteEntity> {
    @Query("SELECT * FROM ${Constants.CITY_TABLE_NAME}")
    suspend fun getFavoriteList(): List<CityFavoriteEntity>

    @Query("SELECT * FROM ${Constants.CITY_TABLE_NAME} WHERE id = :favoriteId")
    suspend fun getFavorite(favoriteId: Int): CityFavoriteEntity?

    @Query("DELETE FROM ${Constants.CITY_TABLE_NAME}")
    suspend fun deleteFavoriteList()

    @Query("DELETE FROM ${Constants.CITY_TABLE_NAME} WHERE id = :favoriteId")
    suspend fun deleteFavoriteById(favoriteId: Int)
}