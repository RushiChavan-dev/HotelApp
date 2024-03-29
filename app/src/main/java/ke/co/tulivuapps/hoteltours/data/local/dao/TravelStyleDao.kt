package ke.co.tulivuapps.hoteltours.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import ke.co.tulivuapps.hoteltours.data.local.base.BaseDao
import ke.co.tulivuapps.hoteltours.data.model.travelstyle.TravelStyleFavoriteEntity
import ke.co.tulivuapps.hoteltours.data.remote.utils.Constants

/**
 * Created by Rushi on 27.03.2023
 */

@Dao
interface TravelStyleDao : BaseDao<TravelStyleFavoriteEntity> {
    @Query("SELECT * FROM ${Constants.TRAVEL_STYLE_TABLE_NAME}")
    suspend fun getFavoriteList(): List<TravelStyleFavoriteEntity>

    @Query("SELECT * FROM ${Constants.TRAVEL_STYLE_TABLE_NAME} WHERE id = :favoriteId")
    suspend fun getFavorite(favoriteId: Int): TravelStyleFavoriteEntity?

    @Query("DELETE FROM ${Constants.TRAVEL_STYLE_TABLE_NAME}")
    suspend fun deleteFavoriteList()

    @Query("DELETE FROM ${Constants.TRAVEL_STYLE_TABLE_NAME} WHERE id = :favoriteId")
    suspend fun deleteFavoriteById(favoriteId: Int)
}