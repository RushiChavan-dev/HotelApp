package ke.co.tulivuapps.hoteltours.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Rushi on 11.03.2023
 */

@Parcelize
data class OriginResponse(
    val name: String?,
    val url: String?,
) : Parcelable