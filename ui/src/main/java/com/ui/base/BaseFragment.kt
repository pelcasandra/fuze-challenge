package com.ui.base

import androidx.fragment.app.Fragment

abstract class BaseFragment<ViewModel: androidx.lifecycle.ViewModel>: Fragment() {

    abstract val viewModel: ViewModel
}