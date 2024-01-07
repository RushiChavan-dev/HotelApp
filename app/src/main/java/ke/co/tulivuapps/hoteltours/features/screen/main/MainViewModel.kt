package ke.co.tulivuapps.hoteltours.features.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ke.co.tulivuapps.hoteltours.data.local.DataStoreOperation
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStoreOperation: DataStoreOperation
) : ViewModel() {

//    var onBoarded : Boolean? =  null

    val onBoarded =  dataStoreOperation.readOnBoardingState().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        false
    )

//    init{
//        viewModelScope.launch {
//            onBoarded =  dataStoreOperation.readOnBoardingState().first()
//        }
//    }

    //val recipes = dataStoreOperation.readOnBoardingState()
    //repository.requestFavoriteRecipes().asLiveData()

}