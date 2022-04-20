package lab.uro.kitori.samplemultimodule.presentation.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import lab.uro.kitori.samplemultimodule.domain.usecase.IGitHubUseCase
import lab.uro.kitori.samplemultimodule.presentation.list.item.ListScreenItem
import lab.uro.kitori.samplemultimodule.presentation.ui.data.ScreenState
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val app: Application,
    private val useCase: IGitHubUseCase
) : AndroidViewModel(app) {
    private val _state = MutableLiveData<ScreenState>(ScreenState.Init)
    val state: LiveData<ScreenState> = _state

    private val _transition = MutableLiveData<String>()
    val transition: LiveData<String> = _transition

    private val _finish = MutableLiveData<Unit>()
    val finish: LiveData<Unit> = _finish

    fun onShowScreen() = viewModelScope.launch {
        _state.value = ScreenState.Loading

        runCatching {
            useCase.getUsers()
        }.onSuccess {
            _state.value = ScreenState.ShowContent(
                ListScreenItem(it)
            )
        }.onFailure {
            _state.value = ScreenState.Error(app.getString(R.string.list_error_message))
        }
    }

    fun onClickItem(name: String) {
        _transition.postValue(name)
    }

    fun onDismissError() {
        _finish.postValue(Unit)
    }
}
