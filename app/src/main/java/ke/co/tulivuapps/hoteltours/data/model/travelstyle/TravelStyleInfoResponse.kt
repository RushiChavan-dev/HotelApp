package ke.co.tulivuapps.hoteltours.data.model.travelstyle

/**
 * Created by Rushi on 12.03.2023
 */

data class TravelStyleInfoResponse(
    val localId: Int?,
    val id: String?,
    val styleName : String,
    val publicId : String,
    val url      : String,
    val status   : String,
    val img: String
)
