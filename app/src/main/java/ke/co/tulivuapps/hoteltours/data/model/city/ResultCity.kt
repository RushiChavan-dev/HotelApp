package ke.co.tulivuapps.hoteltours.data.model.city

import android.os.Parcelable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize


@Parcelize
data class ResultCity(
    val localId: Int? =0,
    val id : String,
    val cityName : String,
    val publicId : String,
    val url   :   String,
    val status  : String
    ) : Parcelable {

    companion object {
        fun create(jsonString: String): ResultCity? {
            return try {
//                jsonString.fromJson<ResultCity>(jsonString)
                Gson().fromJson(jsonString, ResultCity::class.java)
            } catch (e: Exception) {
                return null
            }
        }
    }
}