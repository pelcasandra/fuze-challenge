package com.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.domain.match.GetMatches
import com.domain.match.models.Match
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getMatches: GetMatches) : ViewModel() {
    private val _viewState = MutableStateFlow(MainViewState(loading = true))
    val viewState: StateFlow<MainViewState> = _viewState

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch {
            getMatches.execute()
                .flowOn(Dispatchers.Main)
                .catch {
                    it.message
                }
                .collect {
                    _viewState.value = MainViewState(it, false)
                }
        }
    }

    fun refresh() {
        _viewState.value = _viewState.value.copy(isRefreshing = true)
        load()
    }
}

data class MainViewState(
    val matches: List<Match> = emptyList(),
    val loading: Boolean,
    val isRefreshing: Boolean = false
)