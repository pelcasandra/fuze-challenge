package com.domain.match

import androidx.paging.filter
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class GetMatches @Inject constructor(private val repository: MatchRepository) {
    fun execute(list: List<String>) = repository.getMatches(list.joinToString()).map { paging ->
        paging.filter { match ->
            !match.opponents.isNullOrEmpty()
            && (match.opponents.size) > 1
        }
    }
}