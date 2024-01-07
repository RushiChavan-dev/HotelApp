package ke.co.tulivuapps.hoteltours.data.model.destination

import android.os.Parcelable
import ke.co.tulivuapps.hoteltours.data.model.InfoResponse
import ke.co.tulivuapps.hoteltours.data.model.Result
import kotlinx.parcelize.Parcelize

/**
 * Created by Rushi on 10.03.2023
 */

@Parcelize
data class DestinationResponse(
    val info: InfoResponse,
    val results: List<Result>
) : Parcelable