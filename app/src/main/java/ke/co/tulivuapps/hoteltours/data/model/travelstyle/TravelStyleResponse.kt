package ke.co.tulivuapps.hoteltours.data.model.travelstyle

import android.os.Parcelable
import ke.co.tulivuapps.hoteltours.data.model.InfoResponse
import kotlinx.parcelize.Parcelize

/**
 * Created by Rushi on 10.03.2023
 */

@Parcelize
data class TravelStyleResponse(
    val info: InfoResponse,
    val results: List<ResultTravelStyle>
) : Parcelable