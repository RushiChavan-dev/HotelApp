package ke.co.tulivuapps.hoteltours.data.local.base

import androidx.room.*

/**
 * Created by Rushi on 27.03.2023
 */

@Dao
interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: List<T>)

    @Update
    suspend fun update(data: T)

    @Delete
    suspend fun delete(data: T)
}