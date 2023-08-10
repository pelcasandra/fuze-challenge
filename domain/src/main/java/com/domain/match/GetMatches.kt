package com.domain.match

import com.domain.match.models.Status
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class GetMatches @Inject constructor(private val repository: MatchRepository) {
    fun execute() = repository.getMatches().map { matches ->
        matches
            .sortedBy { it.beginAt }
            .filter { match ->
                !match.opponents.isNullOrEmpty() && match.opponents.size > 1 && match.games.any { it.status != Status.Finished }
            }
    }
}