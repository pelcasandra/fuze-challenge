package com.ui.main

import com.ui.base.theme.Dimens
import com.ui.base.theme.FuzeTheme
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.fragment.app.viewModels
import com.fuze.R
import com.ui.base.BaseFragment
import com.ui.base.components.MatchCard
import com.ui.base.theme.custom.Typography
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<MainViewModel>() {

    override val viewModel by viewModels<MainViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setContent {
            val state = viewModel.viewState.collectAsState()

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

                    if (state.value.loading) Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                    else LazyColumn {
                        itemsIndexed(state.value.matches) { index, item ->
                            MatchCard(
                                modifier = if (index != 0) Modifier.padding(top = Dimens.xlarge_padding)
                                else Modifier.padding(top = Dimens.small_padding),
                                match = item
                            )
                        }
                    }
                }
            }
        }
    }
}