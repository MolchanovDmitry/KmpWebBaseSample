package com.example.playermodels

sealed class PlayerState

object UndefinedState : PlayerState()

object LoadingState : PlayerState()

data class ReadyState(
        val player: Player,
        val isPlaying: Boolean,
        val position: Long,
        val duration: Long,
) : PlayerState()

object EndState : PlayerState()