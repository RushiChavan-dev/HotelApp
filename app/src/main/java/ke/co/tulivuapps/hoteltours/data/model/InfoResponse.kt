package ke.co.tulivuapps.hoteltours.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InfoResponse(
    val count: Int?,
    val next: String?,
    val pages: Int?,
    val prev: String?
) : Parcelable