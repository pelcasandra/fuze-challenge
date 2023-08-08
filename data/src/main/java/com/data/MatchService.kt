package com.data

import com.data.models.MatchResponse
import retrofit2.http.GET

interface MatchService {

    @GET("matches")
    suspend fun getMatches(): List<MatchResponse>
}

