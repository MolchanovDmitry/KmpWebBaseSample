package com.example.playermodels

/**
 * Общий интерфейс плеера для android + ios
 */
interface Player {

    // реализовать [StateFlow] под иос и удалить.
    var listener: PlayerListener?

    fun load()

    fun mute()

    fun unMute()

    fun resume()

    fun pause()

    fun clear()
}

interface PlayerListener{

    fun onStateChanged(playerState: PlayerState)
}