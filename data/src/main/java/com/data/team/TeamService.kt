package com.data.team

import com.data.team.models.TeamResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface TeamService {

    @GET("teams")
    suspend fun getTeams(@Query("filter[id]", encoded = true) id: String): List<TeamResponse>
}

