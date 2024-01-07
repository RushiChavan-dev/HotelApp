package ke.co.tulivuapps.hoteltours.features.screen.settings

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ke.co.tulivuapps.hoteltours.HobbyHorseToursApp
import ke.co.tulivuapps.hoteltours.domain.viewstate.IViewEvent
import ke.co.tulivuapps.hoteltours.domain.viewstate.settings.SettingsViewState
import ke.co.tulivuapps.hoteltours.features.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Rushi on 22.03.2023
 */

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val application: HobbyHorseToursApp
) : BaseViewModel<SettingsViewState, SettingsViewEvent>() {

    init {
        setState { currentState.copy(isDark = false) }
    }

   private fun onChangeTheme() {
        viewModelScope.launch {
//            application.toggleTheme()
            setState { currentState.copy(isDark = false) }
        }
    }

    override fun onTriggerEvent(event: SettingsViewEvent) {
        viewModelScope.launch {
            when (event) {
                is SettingsViewEvent.OnChangeTheme -> onChangeTheme()
            }
        }
    }

    override fun createInitialState() = SettingsViewState()
}

sealed class SettingsViewEvent : IViewEvent {
    object OnChangeTheme : SettingsViewEvent()
}
