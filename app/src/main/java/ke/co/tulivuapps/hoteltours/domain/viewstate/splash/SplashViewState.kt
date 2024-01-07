package ke.co.tulivuapps.hoteltours.domain.viewstate.splash

import androidx.compose.runtime.Stable
import ke.co.tulivuapps.hoteltours.domain.viewstate.IViewState

/**
 * Created by Rushi on 12.03.2023
 */

@Stable
data class SplashViewState(
    val isLoading: Boolean = false
) : IViewState