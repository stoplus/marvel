package com.smart.presentation.api

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavDirections

interface Router {
    fun navigate(navDirections: NavDirections)
    fun navigate(@IdRes navId: Int)
    fun navigate(@IdRes navId: Int, args: Bundle)
    fun navigateUp()
    fun attach(navController: NavController)
    fun detach()
}