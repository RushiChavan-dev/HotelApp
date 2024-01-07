package ke.co.tulivuapps.hoteltours.data.model.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Rushi on 10.03.2023
 */

@Parcelize
data class UserResponse(
    val results: ResultUser
) : Parcelable