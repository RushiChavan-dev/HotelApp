package ke.co.tulivuapps.hoteltours.data.model.character

import ke.co.tulivuapps.hoteltours.data.model.LocationResponse
import ke.co.tulivuapps.hoteltours.data.model.OriginResponse
import ke.co.tulivuapps.hoteltours.data.model.Status

/**
 * Created by Rushi on 12.03.2023
 */

data class CharacterInfoResponse(
    val created: String?,
    val episode: List<String>?,
    val gender: String?,
    val id: Int?,
    val image: String?,
    val location: LocationResponse?,
    val name: String?,
    val origin: OriginResponse?,
    val species: String?,
    val status: Status?,
    val type: String?,
    val url: String?
)
