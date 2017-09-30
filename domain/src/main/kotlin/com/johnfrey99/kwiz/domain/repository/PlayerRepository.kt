package com.johnfrey99.kwiz.domain.repository

import com.johnfrey99.kwiz.domain.model.Player

interface PlayerRepository {
    fun savePlayer(player: Player)
}