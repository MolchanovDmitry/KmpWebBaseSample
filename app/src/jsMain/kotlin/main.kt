import androidx.compose.runtime.*
import com.example.playermodels.*
import kotlinx.browser.document
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable
import org.w3c.dom.HTMLMediaElement


fun main() {
    val video = document.getElementById("video") as HTMLMediaElement
    val jsPlayer = JsPlayer(video)
    jsPlayer.load()

    renderComposable(rootElementId = "root") {
        val videoState: PlayerState by jsPlayer.stateFlow.collectAsState(UndefinedState)
        Div({ style { padding(25.px) } }) {
            PrintState(videoState)
        }
    }
}

@Composable
fun PrintState(playerState: PlayerState){
    val strState = when(val state = playerState){
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