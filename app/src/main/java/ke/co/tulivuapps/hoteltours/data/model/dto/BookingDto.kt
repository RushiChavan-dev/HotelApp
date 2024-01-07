package ke.co.tulivuapps.hoteltours.data.model.dto

import android.os.Parcelable
import ke.co.tulivuapps.hoteltours.data.model.img
import kotlinx.parcelize.Parcelize

/**
 * Created by Rushi on 27.03.2023
 */

@Parcelize
data class BookingDto(
    val id       :   String,
    val createdAt :  String,
    val sessionId  : String?,
    val hotellId    : String,
    val description : String,
    val startDate   : String,
    val endDate     : String,
    val img         : img?,
    val lat         : String,
    val location    : String,
    val long        : String,
    val price       : String,
    val status      : String,
    val star        : String,
    val title       : String,
    val total       : String?,
    val userEmail   : String,
    val cityId      : String,
) : Parcelable {
    companion object {
        fun init() = BookingDto(
            id       = "",
            createdAt = "",
            sessionId  = "",
            hotellId    = "",
            description = "",
            startDate   = "",
            endDate     = "",
            img         = null,
            lat         = "",
            location    = "",
            long        = "",
            price       = "",
            status      = "",
            star        = "",
            title       = "",
            total       = "",
            userEmail   = "",
            cityId      = "",
        )
    }
}