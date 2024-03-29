package ke.co.tulivuapps.hoteltours.features.screen.splash

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ke.co.tulivuapps.hoteltours.domain.viewstate.IViewEvent
import ke.co.tulivuapps.hoteltours.domain.viewstate.splash.SplashViewState
import ke.co.tulivuapps.hoteltours.features.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Rushi on 12.03.2023
 */

@HiltViewModel
class SplashViewModel @Inject constructor(
) : BaseViewModel<SplashViewState, SplashViewEvent>() {

    init {
        viewModelScope.launch {
            delay(1000)
            setEvent(SplashViewEvent.DirectToDashBoard)
        }
    }

    override fun createInitialState() = SplashViewState()
    override fun onTriggerEvent(event: SplashViewEvent) {}
}

sealed class SplashViewEvent : IViewEvent {
    object DirectToDashBoard : SplashViewEvent()
}