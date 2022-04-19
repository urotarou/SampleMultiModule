package lab.uro.kitori.samplemultimodule.presentation.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import lab.uro.kitori.samplemultimodule.domain.usecase.GitHubUseCase
import lab.uro.kitori.samplemultimodule.presentation.detail.item.DetailScreenItem
import lab.uro.kitori.samplemultimodule.presentation.ui.data.ScreenState
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val app: Application,
    private val useCase: GitHubUseCase
) : AndroidViewModel(app) {
    private val _state = MutableLiveData<ScreenState>(ScreenState.Init)
    val state: LiveData<ScreenState> = _state

    private val _finish = MutableLiveData<Unit>()
    val finish: LiveData<Unit> = _finish

    fun onShowScreen(name: String) = viewModelScope.launch {
        _state.value = ScreenState.Loading

        runCatching {
            useCase.getUser(name)
        }.onSuccess {
            _state.value = ScreenState.ShowContent(
                DetailScreenItem(it.name, it.url)
            )
        }.onFailure {
            _state.value = ScreenState.Error(app.getString(R.string.detail_error_message))
        }
    }

    fun onDismissError() {
        _finish.postValue(Unit)
    }
}
