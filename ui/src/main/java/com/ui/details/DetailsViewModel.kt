package com.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.domain.team.GetTeams
import com.domain.team.models.Player
import com.domain.team.models.Team
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val getTeams: GetTeams) : ViewModel() {
    private val _viewState = MutableStateFlow(DetailsViewState(loading = true))
    val viewState: StateFlow<DetailsViewState> = _viewState

    fun start(listId: List<Int>) {
        viewModelScope.launch {
            getTeams.execute(listId)
                .flowOn(Dispatchers.Main)
                .catch {
                    it.message
                }
                .collect { teams ->
                    _viewState.value = DetailsViewState(
                        teams.first { it.id == listId[0] }.players.addEmptyPlayer(),
                        teams.first { it.id == listId[1] }.players.addEmptyPlayer(),
                        false
                    )
                }
        }
    }

    private fun List<Player>.addEmptyPlayer() = mutableListOf<Player>().apply {
        addAll(this@addEmptyPlayer)
        while (this.size <= 5) {
            add(Player(id = 0, name = "", nickName = "", image = "", modified = null))
        }
    }
}

data class DetailsViewState(
    val playersFirstTeam: List<Player> = emptyList(),
    val playersSecondTeam: List<Player> = emptyList(),
    val loading: Boolean
)