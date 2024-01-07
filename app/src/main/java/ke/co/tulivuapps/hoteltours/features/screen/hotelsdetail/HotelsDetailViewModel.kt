package ke.co.tulivuapps.hoteltours.features.screen.hotelsdetail

import androidx.lifecycle.SavedStateHandle
import ke.co.tulivuapps.hoteltours.data.model.Result
import ke.co.tulivuapps.hoteltours.domain.viewstate.IViewEvent
import ke.co.tulivuapps.hoteltours.features.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ke.co.tulivuapps.hoteltours.domain.viewstate.hotelsdetail.HotelsDetailViewState
import javax.inject.Inject

/**
 * Created by Rushi on 13.03.2023
 */
@HiltViewModel
class HotelsDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<HotelsDetailViewState, HotelsDetailViewEvent>() {

    init {
        savedStateHandle.get<String>("hotelDetail")?.let {
            setState { currentState.copy(isLoading = false, data = Result.create(it)) }
        } ?: kotlin.run {
           setEvent(HotelsDetailViewEvent.SnackBarError("Something went wrong"))
        }
    }

    override fun createInitialState() = HotelsDetailViewState()
    override fun onTriggerEvent(event: HotelsDetailViewEvent) {}
}

sealed class HotelsDetailViewEvent : IViewEvent {
    class SnackBarError(val message: String?) : HotelsDetailViewEvent()
}