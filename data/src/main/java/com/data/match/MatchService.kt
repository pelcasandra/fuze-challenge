package com.data.match

import com.data.match.models.MatchResponse
import com.data.team.models.TeamResponse
import retrofit2.http.GET

interface MatchService {
    @GET("matches")
    suspend fun getMatches(): List<MatchResponse>
}

