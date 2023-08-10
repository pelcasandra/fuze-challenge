package com.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.domain.match.GetMatches
import com.domain.match.models.Match
import com.domain.utils.formatToString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import org.jetbrains.annotations.TestOnly
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getMatches: GetMatches) : ViewModel() {
    private val _state = MutableStateFlow(MainViewState())
    val state: StateFlow<MainViewState> = _state

    init {
        load()
    }

    @TestOnly
    fun load() {
        _state.value = MainViewState(
            matches = getMatches.execute(
                listOf(
                    Calendar.getInstance().time.formatToString(),
                    Calendar.getInstance().time.apply { month += 3 }.formatToString()
                )
            ).cachedIn(viewModelScope)
        )
    }
}

data class MainViewState(val matches: Flow<PagingData<Match>> = flow { })