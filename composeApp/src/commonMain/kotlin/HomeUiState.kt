import model.Character
import util.ScreenState

data class HomeUiState(
    val screenState: ScreenState = ScreenState.LOADING,
    val characters: List<Character> = listOf()
)