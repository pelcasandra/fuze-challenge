package com.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.viewModelScope
import androidx.paging.DifferCallback
import androidx.paging.NullPaddedList
import androidx.paging.PagingData
import androidx.paging.PagingDataDiffer
import com.domain.match.GetMatches
import com.domain.match.MatchRepository
import com.domain.match.models.League
import com.domain.match.models.Match
import com.domain.match.models.Serie
import com.domain.match.models.Status
import com.domain.utils.formatToString
import com.ui.main.MainViewModel
import io.mockk.*
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.Calendar

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val instantTask = InstantTaskExecutorRule()

    @MockK
    private val matchRepository = mockk<MatchRepository>()

    private val getMatches = GetMatches(matchRepository)

    private val viewModel by lazy {
        spyk(MainViewModel(getMatches))
    }

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun `GIVEN a fake information WHEN getting it THEN return success value`() {
        // When
        coEvery {
            matchRepository.getMatches(
                listOf(
                    Calendar.getInstance().time.formatToString(),
                    Calendar.getInstance().time.apply { month += 3 }.formatToString()
                ).joinToString()
            )
        } returns flow { PagingData.from(list) }

        coEvery {
            getMatches.execute(
                listOf(
                    Calendar.getInstance().time.formatToString(),
                    Calendar.getInstance().time.apply { month += 3 }.formatToString()
                )
            )
        } returns flow { PagingData.from(list) }

        viewModel.load()

        viewModel.viewModelScope.launch {
            assertTrue(viewModel.state.value.matches.first().collectDataForTest().isEmpty())
        }
    }

    companion object {
        val list = listOf(
            Match(
                id = 0,
                beginAt = null,
                games = emptyList(),
                league = League(0, "", ""),
                serie = Serie(0, ""),
                status = Status.Running,
                opponents = emptyList()
            ),
            Match(
                id = 1,
                beginAt = null,
                games = emptyList(),
                league = League(1, "", ""),
                serie = Serie(1, ""),
                status = Status.Running,
                opponents = emptyList()
            )
        )
    }
}

private suspend fun <T : Any> PagingData<T>.collectDataForTest(): List<T> {
    val dcb = object : DifferCallback {
        override fun onChanged(position: Int, count: Int) {}
        override fun onInserted(position: Int, count: Int) {}
        override fun onRemoved(position: Int, count: Int) {}
    }
    val items = mutableListOf<T>()
    val dif = object : PagingDataDiffer<T>(dcb, TestCoroutineDispatcher()) {
        override suspend fun presentNewList(
            previousList: NullPaddedList<T>,
            newList: NullPaddedList<T>,
            lastAccessedIndex: Int,
            onListPresentable: () -> Unit
        ): Int? {
            for (idx in 0 until newList.size)
                items.add(newList.getFromStorage(idx))
            onListPresentable()
            return null
        }
    }
    dif.collectFrom(this)
    return items
}