package com.smart.base

import androidx.annotation.MainThread
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

abstract class ViewBindingProperty<in R : Any, T : ViewBinding>(
    private val viewBinder: (R) -> T
) : ReadOnlyProperty<R, T> {

    private var viewBinding: T? = null
    private val lifecycleObserver = ClearOnDestroyLifecycleObserver()
    private var thisRef: R? = null

    protected abstract fun getLifecycleOwner(thisRef: R): LifecycleOwner

    @MainThread
    override fun getValue(thisRef: R, property: KProperty<*>): T {
        viewBinding?.let { return it }

        this.thisRef = thisRef

        val lifecycle = getLifecycleOwner(thisRef).lifecycle
        val viewBinding = viewBinder(thisRef)

        if (lifecycle.currentState == Lifecycle.State.DESTROYED) {
            MainHandler.runOnUiThread({ this.viewBinding = null })
            // Можно получить доступ к ViewBinding после onDestroy,
            // но потом лучше затереть ссылку, чтобы не было утечек
        } else {
            lifecycle.addObserver(lifecycleObserver)
            this.viewBinding = viewBinding
        }
        return viewBinding
    }

    @MainThread
    fun clear() {
        val thisRef = thisRef ?: return
        this.thisRef = null

        getLifecycleOwner(thisRef).lifecycle.removeObserver(lifecycleObserver)

        MainHandler.runOnUiThread({ this.viewBinding = null })
    }

    private inner class ClearOnDestroyLifecycleObserver : LifecycleObserver {

        @MainThread
        fun onDestroy(owner: LifecycleOwner): Unit = clear()
    }
}