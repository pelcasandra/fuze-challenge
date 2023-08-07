package com.ui.main

import com.ui.base.theme.Dimens
import com.ui.base.theme.FuzeTheme
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
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

class MainFragment : BaseFragment<MainViewModel>() {

    override val viewModel by viewModels<MainViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setContent {
            FuzeTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = Dimens.xlarge_padding)
                        .padding(horizontal = Dimens.large_padding)
                ) {
                    Text(
                        text = stringResource(id = R.string.main_title),
                        style = Typography.Label.large.copy(fontSize = 30.sp),
                        color = Color.White
                    )

                    MatchCard(modifier = Modifier.padding(top = Dimens.xlarge_padding))
                }
            }
        }
    }
}