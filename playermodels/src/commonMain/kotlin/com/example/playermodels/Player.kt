package com.example.playermodels

/**
 * Общий интерфейс плеера для android + ios
 */
interface Player {

    var listener: NutPlayerListener?

    fun load()

    fun mute()

    fun unMute()

    fun resume()

    fun pause()

    fun clear()
}

// TODO заменить на stateFlow в будущем.
interface NutPlayerListener{

    fun onStateChanged(nutPlayerState: PlayerState)
}