package com.smart.presentation.api

import android.content.Context
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavDirections

abstract class Router {
    abstract fun navigate(navDirections: NavDirections)
    abstract fun navigate(@IdRes navId: Int)
    abstract fun navigate(@IdRes navId: Int, args: Bundle)
    abstract fun navigateUp()
    abstract fun attach(navController: NavController)
    abstract fun detach()
}