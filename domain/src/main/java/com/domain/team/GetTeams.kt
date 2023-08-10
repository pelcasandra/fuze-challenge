package com.domain.team

import kotlinx.coroutines.flow.map
import javax.inject.Inject


class GetTeams @Inject constructor(private val repository: TeamRepository) {
    fun execute(listId: List<Int>) = repository.getTeams(listId.joinToString()).map { teams ->
        teams.map { team -> team.copy(players = team.players.sortedByDescending { it.modified }) }
    }
}

