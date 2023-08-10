package com.ui.main

import com.ui.base.theme.Dimens
import com.ui.base.theme.FuzeTheme
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.domain.match.models.Match
import com.fuze.R
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ui.base.BaseFragment
import com.ui.base.components.MatchCard
import com.ui.base.extension.safeNavigate
import com.ui.base.theme.custom.Typography
import com.ui.details.DetailsFragmentArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<MainViewModel>() {

    override val viewModel by viewModels<MainViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setContent {
            val matches = viewModel.state.collectAsState().value.matches.collectAsLazyPagingItems()
            val isFirsLoading = rememberSaveable { mutableStateOf(true) }

            FuzeTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = Dimens.xlarge_padding)
                        .padding(horizontal = Dimens.large_padding)
                ) {
                    Text(
                        modifier = Modifier.padding(bottom = Dimens.large_padding),
                        text = stringResource(id = R.string.main_title),
                        style = Typography.Label.large.copy(fontSize = 30.sp),
                        color = Color.White
                    )

                    Matches(matches = matches, isFirsLoading = isFirsLoading) { item, matchTime ->
                        findNavController().safeNavigate(
                            R.id.detailsFragment,
                            DetailsFragmentArgs(
                                "${item.league.name} ${item.serie.name}",
                                matchTime,
                                item.opponents!![0],
                                item.opponents!![1]
                            ).toBundle()
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun Matches(
    matches: LazyPagingItems<Match>,
    isFirsLoading: MutableState<Boolean>,
    onClick: (Match, String) -> Unit
) {
    val state =
        rememberSwipeRefreshState(isRefreshing = matches.loadState.refresh is LoadState.Loading)

    SwipeRefresh(
        state = state,
        onRefresh = { matches.refresh() }
    ) {
        LazyColumn {
            itemsIndexed(matches) { index, item ->
                item?.let {
                    isFirsLoading.value = false

                    MatchCard(
                        modifier = if (index != 0) Modifier.padding(top = Dimens.xlarge_padding)
                        else Modifier.padding(top = Dimens.small_padding),
                        match = item
                    ) {
                        onClick.invoke(item, it)
                    }
                }
            }

            if (matches.loadState.append is LoadState.Loading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = Dimens.medium_padding)
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                            color = Color.White
                        )
                    }
                }
            }

            if (matches.loadState.refresh is LoadState.Loading && isFirsLoading.value) {
                item { Box(modifier = Modifier.fillParentMaxSize()) {} }
            }
        }
    }
}
