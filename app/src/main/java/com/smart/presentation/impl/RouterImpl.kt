package com.smart.presentation.impl

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.smart.presentation.api.Router

class RouterImpl : Router {
    private var navController: NavController? = null

    override fun navigate(navDirections: NavDirections) {
        navController?.navigate(navDirections)
    }

    override fun navigate(@IdRes navId: Int) {
        navController?.navigate(navId)
    }

    override fun navigate(@IdRes navId: Int, args: Bundle) {
        navController?.navigate(navId, args)
    }

    override fun navigateUp() {
        navController?.navigateUp()
    }

    override fun attach(navController: NavController) {
        this.navController = navController
    }

    override fun detach() {
        navController = null
    }
}
