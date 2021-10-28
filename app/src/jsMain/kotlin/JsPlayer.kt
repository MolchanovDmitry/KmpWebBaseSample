import com.example.playermodels.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.dom.clear
import org.w3c.dom.HTMLMediaElement

class JsPlayer(private val video: HTMLMediaElement) : Player {

    private val _stateFlow = MutableStateFlow<PlayerState>(UndefinedState)
    val stateFlow = _stateFlow.asStateFlow()

    private val provider = FakeProvider()
    private val uiScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private var state: PlayerState = UndefinedState
        set(value) {
            field = value
            _stateFlow.value = value
        }

    init {
        initListeners()
    }

    // реализовать [StateFlow] под иос и удалить.
    override var listener: NutPlayerListener? = null

    override fun load() {
        state = LoadingState
        uiScope.launch {
            video.src = provider.load()
        }
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
        player = this@JsPlayer,
        isPlaying = isPlaying,
        position = video.currentTime.toLong(),
        duration = video.duration.toLong()
    )
}