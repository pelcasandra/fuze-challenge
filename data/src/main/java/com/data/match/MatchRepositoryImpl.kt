package com.data.match

import com.domain.match.MatchRepository
import com.domain.match.models.Match
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MatchRepositoryImpl @Inject constructor(private val service: MatchService) : MatchRepository {
    override fun getMatches() = flow<List<Match>> {
        emit(service.getMatches().map {
            it.map()
        })
    }.flowOn(Dispatchers.IO)
}