package com.example.playermodels

import NutPlayer

sealed class NutPlayerState

object UndefinedState : NutPlayerState()

object LoadingState : NutPlayerState()

data class ReadyState(
        val nutPlayer: NutPlayer,
        val isPlaying: Boolean,
        val position: Long,
        val duration: Long,
) : NutPlayerState()

object EndState : NutPlayerState()
