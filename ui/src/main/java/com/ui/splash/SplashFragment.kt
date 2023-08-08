package com.ui.splash

import com.ui.base.theme.Colors
import com.ui.base.theme.FuzeTheme
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.fuze.R
import com.ui.base.BaseFragment
import com.ui.base.extension.safeNavigate
import kotlinx.coroutines.delay

class SplashFragment : BaseFragment<SplashViewModel>() {

    override val viewModel by viewModels<SplashViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setContent {
            val isAnimation = remember { mutableStateOf(true) }

            FuzeTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Colors.primary)
                ) {
                    AnimatedVisibility(
                        modifier = Modifier.align(Alignment.Center),
                        visible = isAnimation.value,
                        enter = fadeIn(spring(stiffness = Spring.StiffnessLow)),
                        exit = fadeOut()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = ""
                        )
                    }
                }
            }

            LaunchedEffect(key1 = Unit) {
                delay(1000)
                isAnimation.value = !isAnimation.value
                delay(600)
                findNavController().popBackStack()
                findNavController().safeNavigate(R.id.mainFragment)
            }
        }
    }
}