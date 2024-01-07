package ke.co.tulivuapps.hoteltours.data.local.db

import android.content.Context
import androidx.compose.runtime.Stable
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ke.co.tulivuapps.hoteltours.data.local.converter.PopularConverter
import ke.co.tulivuapps.hoteltours.data.local.dao.CityDao
import ke.co.tulivuapps.hoteltours.data.local.dao.DestinationDao
import ke.co.tulivuapps.hoteltours.data.local.dao.FavoriteDao
import ke.co.tulivuapps.hoteltours.data.local.dao.HotelDao
import ke.co.tulivuapps.hoteltours.data.local.dao.TravelStyleDao
import ke.co.tulivuapps.hoteltours.data.model.city.CityFavoriteEntity
import ke.co.tulivuapps.hoteltours.data.model.destination.DestinationFavoriteEntity
import ke.co.tulivuapps.hoteltours.data.model.FavoriteEntity
import ke.co.tulivuapps.hoteltours.data.model.hotel.HotelFavoriteEntity
import ke.co.tulivuapps.hoteltours.data.model.travelstyle.TravelStyleFavoriteEntity
import ke.co.tulivuapps.hoteltours.data.remote.utils.Constants

/**
 * Created by Rushi on 27.03.2023
 */
@Stable
@Database(
    entities = [FavoriteEntity::class, DestinationFavoriteEntity::class, HotelFavoriteEntity::class, CityFavoriteEntity::class, TravelStyleFavoriteEntity::class],
    version = 3,
    exportSchema = true
)
@TypeConverters(PopularConverter::class)
abstract class HobbyHorseToursDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
    abstract fun destinationDao(): DestinationDao
    abstract fun hotelDao(): HotelDao
    abstract fun travelStyleDao(): TravelStyleDao
    abstract fun cityDao(): CityDao

    companion object {
        @Volatile
        private var instance: HobbyHorseToursDatabase? = null

        fun getDatabase(context: Context): HobbyHorseToursDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, HobbyHorseToursDatabase::class.java, Constants.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}