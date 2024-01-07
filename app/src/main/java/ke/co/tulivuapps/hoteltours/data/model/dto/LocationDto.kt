package ke.co.tulivuapps.hoteltours.data.model.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Rushi on 27.03.2023
 */

@Parcelize
data class LocationDto(
    val locationId: Int,
    val name: String,
    val url: String
) : Parcelable