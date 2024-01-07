package ke.co.tulivuapps.hoteltours.features.screen.destinationsdetail

import androidx.lifecycle.SavedStateHandle
import ke.co.tulivuapps.hoteltours.data.model.Result
import ke.co.tulivuapps.hoteltours.domain.viewstate.IViewEvent
import ke.co.tulivuapps.hoteltours.features.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ke.co.tulivuapps.hoteltours.domain.viewstate.destinationsdetail.DestinationsDetailViewState
import javax.inject.Inject

/**
 * Created by Rushi on 13.03.2023
 */
@HiltViewModel
class DestinationsDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<DestinationsDetailViewState, DestinationsDetailViewEvent>() {

    init {
        savedStateHandle.get<String>("destinationDetail")?.let {
            setState { currentState.copy(isLoading = false, data = Result.create(it)) }
        } ?: kotlin.run {
           setEvent(DestinationsDetailViewEvent.SnackBarError("Something went wrong"))
        }
    }

    override fun createInitialState() = DestinationsDetailViewState()
    override fun onTriggerEvent(event: DestinationsDetailViewEvent) {}
}

sealed class DestinationsDetailViewEvent : IViewEvent {
    class SnackBarError(val message: String?) : DestinationsDetailViewEvent()
}