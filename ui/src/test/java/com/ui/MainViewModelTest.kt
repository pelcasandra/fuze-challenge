package com.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.domain.match.GetMatches
import com.domain.match.models.League
import com.domain.match.models.Match
import com.domain.match.models.Serie
import com.ui.main.MainViewModel
import io.mockk.*
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val instantTask = InstantTaskExecutorRule()

    @MockK
    private lateinit var getMatches: GetMatches

    private val viewModel by lazy {
        spyk(MainViewModel(getMatches))
    }

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true, relaxed = false)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun `GIVEN a fake information WHEN getting it THEN return success value`() {
        // Given
        val list = listOf(
            Match(
                id = 0,
                beginAt = null,
                games = emptyList(),
                league = League(0, "", ""),
                serie = Serie(0, ""),
                opponents = emptyList()
            ),
            Match(
                id = 1,
                beginAt = null,
                games = emptyList(),
                league = League(1, "", ""),
                serie = Serie(1, ""),
                opponents = emptyList()
            )
        )

        // When
        coEvery { getMatches.execute() } returns flow { emit(list) }
        viewModel.refresh()

        //Then
        verify(exactly = 1) { viewModel.refresh() }

        assertEquals(
            list.first().id,
            viewModel.viewState.value.matches.first().id
        )
    }
}
