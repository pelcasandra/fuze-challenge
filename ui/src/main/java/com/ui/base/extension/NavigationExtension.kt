package com.ui.base.extension

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.fuze.R


fun NavController.safeNavigate(@IdRes id: Int, bundle: Bundle? = null, navOptions: NavOptions? = null) {
    val currentGraph = currentDestination?.parent ?: graph

    val useOptions = navOptions ?: PreMadeNavOptions.enterRightExitLeft()
    if (currentGraph.findNode(id) != null) {
        navigate(id, bundle, useOptions)
    }
}

object PreMadeNavOptions {

    fun enterRightExitLeft(
        alsoApply: NavOptions.Builder.() -> NavOptions.Builder = { this }
    ): NavOptions {
        return NavOptions.Builder()
            .setEnterAnim(R.anim.fade_in)
            .setExitAnim(R.anim.fade_out)
            .setPopEnterAnim(R.anim.fade_in)
            .setPopExitAnim(R.anim.fade_out)
            .alsoApply()
            .build()
    }
}
