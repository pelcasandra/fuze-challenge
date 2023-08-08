package com.ui.main

import androidx.lifecycle.ViewModel
import com.domain.GetMatches
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getMatches: GetMatches) : ViewModel() {


}