package com.example.playermodels

/**
 * Общий интерфейс плеера для android + ios
 */
interface Player {

    // реализовать [StateFlow] под иос и удалить.
    var listener: NutPlayerListener?

    fun load()

    fun mute()

    fun unMute()

    fun resume()

    fun pause()

    fun clear()
}

// реализовать [StateFlow] под иос и удалить.
interface NutPlayerListener{

    fun onStateChanged(nutPlayerState: PlayerState)
}