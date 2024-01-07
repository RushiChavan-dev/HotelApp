package ke.co.tulivuapps.hoteltours.domain.viewstate.settings

import androidx.compose.runtime.Stable
import ke.co.tulivuapps.hoteltours.domain.viewstate.IViewState

/**
 * Created by Rushi on 22.03.2023
 */

@Stable
data class SettingsViewState(
    val isDark: Boolean = false,
) : IViewState