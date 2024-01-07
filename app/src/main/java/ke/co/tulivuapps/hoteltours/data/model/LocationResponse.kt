package ke.co.tulivuapps.hoteltours.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Created by Rushi on 11.03.2023
 */

@Parcelize
data class LocationResponse(
    val name: String?,
    val url: String?
) : Parcelable
