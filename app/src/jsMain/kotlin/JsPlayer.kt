import com.example.playermodels.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.dom.clear
import org.w3c.dom.HTMLMediaElement

class JsPlayer(private val video: HTMLMediaElement) : NutPlayer {

    private val _stateFlow = MutableStateFlow<NutPlayerState>(UndefinedState)
    val stateFlow = _stateFlow.asStateFlow()

    private var state: NutPlayerState = UndefinedState
        set(value) {
            field = value
            _stateFlow.value = value
        }

    init {
        initListeners()
    }

    override fun load() {
        state = LoadingState
        video.src = "https://clck.ru/YQb6f"
    }

    override fun mute() {
    }

    override fun unMute() {
    }

    override fun resume() {
        video.play()
    }

    override fun pause() {
        video.pause()
    }

    override fun clear() {
        video.clear()
    }

    private fun initListeners() {
        video.addEventListener("playing", callback = {
            state = getReadyState(isPlaying = true)
        })
        video.addEventListener("pause", callback = {
            state = getReadyState(isPlaying = false)
        })
        video.addEventListener("ended", callback = {
            state = EndState
        })
        video.addEventListener("timeupdate", callback = {
            state = getReadyState(isPlaying = true)
        })
    }

    private fun getReadyState(isPlaying: Boolean) = ReadyState(
        nutPlayer = this@JsPlayer,
        isPlaying = isPlaying,
        position = video.currentTime.toLong(),
        duration = video.duration.toLong()
    )
}