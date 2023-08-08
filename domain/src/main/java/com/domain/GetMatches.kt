package com.domain

import javax.inject.Inject


class GetMatches @Inject constructor(private val repository: MatchRepository) {
    fun execute() = repository.getMatches()
}