package ke.co.tulivuapps.hoteltours.data.model.destination

/**
 * Created by Rushi on 12.03.2023
 */

data class DestinationInfoResponse(
    val localId     :     Int ,
    val id     :     String ,
    val title   :    String,
    val description :String,
    val star      :  Float  ,
    val img      :   String,
    val lat     :    Float,
    val location  :  String,
    val long      :  Float,
    val price    :   Float ,
    val offer    :   Boolean,
    val offerPrice : Float   ,
    val userEmail  : String,
    val cityId    :  String ,
    val createdAt  : String,
)
