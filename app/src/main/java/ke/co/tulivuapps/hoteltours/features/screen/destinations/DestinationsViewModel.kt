package ke.co.tulivuapps.hoteltours.features.screen.destinations

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ke.co.tulivuapps.hoteltours.data.model.dto.DestinationDto
import ke.co.tulivuapps.hoteltours.domain.usecase.destinations.GetDestinationsUseCase
import ke.co.tulivuapps.hoteltours.domain.usecase.destinations.UpdateDestinationFavoriteUseCase
import ke.co.tulivuapps.hoteltours.domain.viewstate.IViewEvent
import ke.co.tulivuapps.hoteltours.domain.viewstate.destinations.DestinationsViewState
import ke.co.tulivuapps.hoteltours.features.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Rushi on 13.03.2023
 */

@HiltViewModel
class DestinationsViewModel @Inject constructor(
    private val getDestinationsUseCase: GetDestinationsUseCase,
    private val updateDestinationFavoriteUseCase: UpdateDestinationFavoriteUseCase
) : BaseViewModel<DestinationsViewState, DestinationsViewEvent>() {

    private val config = PagingConfig(pageSize = 20)

    init {
        getAllDestinations()
    }

    private fun getAllDestinations() {
        viewModelScope.launch(Dispatchers.IO) {
            setState { currentState.copy(isLoading = true) }
            val params = GetDestinationsUseCase.Params(config, hashMapOf())
            val pagedFlow = getDestinationsUseCase(params).cachedIn(scope = viewModelScope)
            setState { currentState.copy(isLoading = false, pagedData = pagedFlow) }
        }
    }

    private fun updateDestinationFavorite(dto: DestinationDto) = viewModelScope.launch {
        val params = UpdateDestinationFavoriteUseCase.Params(dto)
        call(updateDestinationFavoriteUseCase(params))
    }

    override fun createInitialState() = DestinationsViewState()

    override fun onTriggerEvent(event: DestinationsViewEvent) {
        viewModelScope.launch {
            when (event) {
                is DestinationsViewEvent.UpdateDestinationFavorite -> updateDestinationFavorite(event.dto)
                else -> {}
            }
        }
    }
}

sealed class DestinationsViewEvent : IViewEvent {
    class UpdateDestinationFavorite(val dto: DestinationDto) : DestinationsViewEvent()
}