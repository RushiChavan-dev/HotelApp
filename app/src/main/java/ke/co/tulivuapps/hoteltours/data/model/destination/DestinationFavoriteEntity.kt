package ke.co.tulivuapps.hoteltours.data.model.destination

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ke.co.tulivuapps.hoteltours.data.remote.utils.Constants


/**
 * Created by Rushi on 27.03.2023
 */


@Entity(tableName = Constants.DESTINATION_TABLE_NAME)
data class DestinationFavoriteEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = Constants.COLUMN_LOCAL_ID)
    val localId: Int,
    @ColumnInfo(name = Constants.COLUMN_ID)
    val id: String,
    @ColumnInfo(name = Constants.COLUMN_TITLE)
    val title: String?,
    @ColumnInfo(name = Constants.COLUMN_DESCRIPTION)
    val description: String,
    @ColumnInfo(name = Constants.COLUMN_STAR)
    val star: String,
    @ColumnInfo(name = Constants.COLUMN_IMG)
    val img: List<String>?,
    @ColumnInfo(name = Constants.COLUMN_LAT)
    val lat: String,
    @ColumnInfo(name = Constants.COLUMN_LOCATION)
    val location: String,
    @ColumnInfo(name = Constants.COLUMN_LANG)
    val lang: String,
    @ColumnInfo(name = Constants.COLUMN_PRICE)
    val price: String,
    @ColumnInfo(name = Constants.COLUMN_OFFER)
    val offer: String,
    @ColumnInfo(name = Constants.COLUMN_OFFER_PRICE)
    val offerPrice: String,
    @ColumnInfo(name = Constants.COLUMN_USER_EMAIL)
    val userEmail: String,
    @ColumnInfo(name = Constants.COLUMN_CITY_ID)
    val cityId: String,
    @ColumnInfo(name = Constants.COLUMN_CREATED_AT)
    val createdAt: String
)