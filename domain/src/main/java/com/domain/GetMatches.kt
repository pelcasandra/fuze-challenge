package com.domain

import kotlinx.coroutines.flow.map
import javax.inject.Inject


class GetMatches @Inject constructor(private val repository: MatchRepository) {
    fun execute() = repository.getMatches().map { matches ->
        matches.filter { !it.opponents.isNullOrEmpty() && it.opponents.size > 1 }
    }
}