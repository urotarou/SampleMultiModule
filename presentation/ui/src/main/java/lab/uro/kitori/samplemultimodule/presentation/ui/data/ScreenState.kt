package lab.uro.kitori.samplemultimodule.presentation.ui.data

sealed class ScreenState {
    object Init : ScreenState()
    object Loading : ScreenState()
    data class ShowContent<T>(val data: T) : ScreenState()
    object Empty : ScreenState()
    data class Error(val errorMessage: String) : ScreenState()
}
