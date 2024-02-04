import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import model.Character
import util.ScreenState

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = getViewModel(Unit, viewModelFactory { HomeViewModel() })
    val state: HomeUiState by viewModel.uiState.collectAsState()

    when (state.screenState) {
        ScreenState.LOADING -> {
            // handle loading animation
        }

        ScreenState.SUCCESS -> {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize().padding(8.dp),
                columns = GridCells.Adaptive(minSize = 128.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.characters) { character ->
                    CharacterCard(character)
                }
            }
        }

        ScreenState.ERROR -> {
            Text("Request failed")
        }
    }
}

@Composable
fun CharacterCard(character: Character) {
    Card {
        Column {
            KamelImage(
                modifier = Modifier
                    .width(300.dp)
                    .height(200.dp),
                resource = asyncPainterResource(data = character.imageUrl),
                contentScale = ContentScale.Crop,
                contentDescription = "A picture of ${character.fullName}"
            )
            Text(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                text = character.fullName,
                textAlign = TextAlign.Center
            )
        }
    }
}