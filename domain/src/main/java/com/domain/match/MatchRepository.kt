package com.domain.match

import com.domain.match.models.Match
import kotlinx.coroutines.flow.Flow

interface MatchRepository {
    fun getMatches(): Flow<List<Match>>
}