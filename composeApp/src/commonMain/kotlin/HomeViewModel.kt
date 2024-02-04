import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import model.Character
import util.ScreenState

class HomeViewModel : ViewModel() {
    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    override fun onCleared() {
        Ktor.client.close()
    }

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        viewModelScope.launch {
            try {
                val characters = Ktor.client.get("Characters").body<List<Character>>()
                _uiState.value = HomeUiState(
                    screenState = ScreenState.SUCCESS,
                    characters = characters
                )
            } catch (e: Exception) {
                _uiState.value = HomeUiState(
                    screenState = ScreenState.ERROR
                )
            }
        }
    }
}