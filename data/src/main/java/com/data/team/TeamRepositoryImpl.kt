package com.data.team

import com.domain.team.TeamRepository
import com.domain.team.models.Team
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TeamRepositoryImpl @Inject constructor(private val service: TeamService) : TeamRepository {
    override fun getTeams(ids: String) = flow<List<Team>> {
        emit(service.getTeams(ids).map { team -> team.map() })
    }.flowOn(Dispatchers.IO)
}