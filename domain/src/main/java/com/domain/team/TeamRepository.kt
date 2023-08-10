package com.domain.team

import com.domain.team.models.Team
import kotlinx.coroutines.flow.Flow

interface TeamRepository {
    fun getTeams(ids: String): Flow<List<Team>>
}