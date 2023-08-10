package com.data.match

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.domain.match.MatchRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MatchRepositoryImpl @Inject constructor(private val service: MatchService) : MatchRepository {
    override fun getMatches(beginAt: String) = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            PagingSource(service = service, beginAt = beginAt)
        }
    ).flow

    companion object {
        const val PAGE_SIZE = 20
    }
}

