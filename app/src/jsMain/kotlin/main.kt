import androidx.compose.runtime.*
import com.example.playermodels.*
import kotlinx.browser.document
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable
import org.w3c.dom.HTMLMediaElement


fun main() {
    val video = document.getElementById("video") as HTMLMediaElement
    val nutJsPlayer = JsPlayer(video)
    nutJsPlayer.load()

    renderComposable(rootElementId = "root") {
        val videoState: PlayerState by nutJsPlayer.stateFlow.collectAsState(UndefinedState)
        Div({ style { padding(25.px) } }) {
            PrintState(videoState)
        }
    }
}

@Composable
fun PrintState(nutPlayerState: PlayerState){
    val strState = when(val state = nutPlayerState){
        UndefinedState -> "UndefinedState"
        LoadingState -> "LoadingState"
        EndState -> "EndState"
        is ReadyState -> {
            val playingState = if(state.isPlaying) "Playing" else "Pause"
            "$playingState position = ${state.position} duration = ${state.duration}"
        }
    }
    Text(strState)
}