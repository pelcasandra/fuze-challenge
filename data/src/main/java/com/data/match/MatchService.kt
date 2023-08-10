package com.data.match

import com.data.match.models.MatchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MatchService {
    @GET("matches")
    suspend fun getMatches(
        @Query("page") page: Int,
        @Query("range[begin_at]") beginAt: String,
        @Query("filter[status]") filter: String = "not_started,running",
        @Query("sort") sort: String = "begin_at,-status",
    ): List<MatchResponse>
}

