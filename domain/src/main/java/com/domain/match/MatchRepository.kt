package com.domain.match

import androidx.paging.PagingData
import com.domain.match.models.Match
import kotlinx.coroutines.flow.Flow

interface MatchRepository {
    fun getMatches(beginAt: String): Flow<PagingData<Match>>
}