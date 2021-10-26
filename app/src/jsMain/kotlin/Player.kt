import com.example.playermodels.NutPlayerState

/**
 * Общий интерфейс плеера для android + ios
 */
interface NutPlayer {

    fun load()

    fun mute()

    fun unMute()

    fun resume()

    fun pause()

    fun clear()
}