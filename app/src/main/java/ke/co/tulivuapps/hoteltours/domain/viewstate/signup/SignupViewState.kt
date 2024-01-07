package ke.co.tulivuapps.hoteltours.domain.viewstate.signup

import androidx.compose.runtime.Stable
import ke.co.tulivuapps.hoteltours.data.model.user.ResultUser
import ke.co.tulivuapps.hoteltours.domain.viewstate.IViewState

/**
 * Created by Rushi on 13.03.2023
 */
@Stable
data class SignupViewState(
    val isLoading: Boolean = false,
    val data: ResultUser? = null,
) : IViewState