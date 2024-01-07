package ke.co.tulivuapps.hoteltours.data.model

import androidx.compose.runtime.Stable

/**
 * Created by Rushi on 12.03.2023
 */

@Stable
data class APIError(val code: Long, val message: String)
