package com.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ui.base.BaseFragment
import com.ui.base.components.MatchTeam
import com.ui.base.components.MatchTeamEntity
import com.ui.base.components.Players
import com.ui.base.components.PlayersTeamEntity
import com.ui.base.theme.Colors
import com.ui.base.theme.Dimens
import com.ui.base.theme.FuzeTheme
import com.ui.base.theme.custom.Typography
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class DetailsFragment : BaseFragment<DetailsViewModel>() {

    override val viewModel by viewModels<DetailsViewModel>()
    private val args by navArgs<DetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        viewModel.start(listOf(args.firstTeam.id, args.secondTeam.id))

        setContent {
            val state = viewModel.viewState.collectAsState()

            FuzeTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                args.title,
                                color = Color.White,
                                style = Typography.Label.small.copy(fontSize = 18.sp)
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = { findNavController().popBackStack() }) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Arrow back",
                                    tint = Color.White
                                )
                            }
                        },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = Colors.primary,
                        )
                    )

                    if (state.value.loading) Box(modifier = Modifier.fillMaxSize().weight(1f)) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center), Color.White)
                    }
                    else {
                        MatchTeam(
                            modifier = Modifier
                                .padding(top = Dimens.xlarge_padding)
                                .align(Alignment.CenterHorizontally),
                            entity = MatchTeamEntity(args.firstTeam, args.secondTeam)
                        )

                        Text(
                            modifier = Modifier
                                .padding(vertical = Dimens.large_padding)
                                .align(Alignment.CenterHorizontally),
                            text = args.matchTime,
                            style = Typography.Label.xsmall.copy(fontSize = 10.sp),
                            color = Color.White,
                            textAlign = TextAlign.End
                        )

                        repeat(5) {
                            Players(
                                entity = PlayersTeamEntity(
                                    state.value.playersFirstTeam[it],
                                    state.value.playersSecondTeam[it]
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}