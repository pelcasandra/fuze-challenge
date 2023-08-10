package com.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.domain.GetMatches
import com.domain.models.Match
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
    private val _viewState = MutableStateFlow(ViewState(loading = true))
    val viewState: StateFlow<ViewState> = _viewState

    init {
        viewModelScope.launch {
            getMatches.execute()
                .flowOn(Dispatchers.Main)
                .catch {
                    it.message
                }
                .collect {
                    _viewState.value = ViewState(it, false)
                }
        }
    }
}

data class ViewState(
    val matches: List<Match> = emptyList(),
    val loading: Boolean
)