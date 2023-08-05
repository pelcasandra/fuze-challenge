package com.fuze.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fuze.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainNavActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_nav)
    }
}