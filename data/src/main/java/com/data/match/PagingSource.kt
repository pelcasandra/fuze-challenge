package com.data.match

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.domain.match.models.Match
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PagingSource(
    private val service: MatchService,
    private val beginAt: String
) : PagingSource<Int, Match>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Match> {
        return withContext(Dispatchers.IO) {
            try {
                val result = service.getMatches(page = params.key ?: STARTING_PAGE_INDEX, beginAt = beginAt)
                val list = result.map { it.map() }

//                val runningList = mapedList.filter { it. }
                LoadResult.Page(
                    data = list,
                    prevKey = null,
                    nextKey = if (result.isNotEmpty()) params.key?.plus(1)
                        ?: STARTING_PAGE_INDEX.plus(1) else null
                )
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, Match>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}