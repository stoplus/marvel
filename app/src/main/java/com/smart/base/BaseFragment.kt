package com.smart.base

import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData

abstract class BaseFragment : Fragment {
    constructor() : super()
    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    protected var backPressedCallback: OnBackPressedCallback? = null
        private set


    protected fun <T> LiveData<T>.observe(block: (T) -> Unit) {
        observe(viewLifecycleOwner, { it?.let(block) })
    }

    protected fun <T> LiveData<T>.observeNulls(block: (T?) -> Unit) {
        observe(viewLifecycleOwner, { block(it) })
    }

    protected fun addBackPressedCallback(callback: OnBackPressedCallback) {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callback)
        backPressedCallback = callback
    }

    protected fun addBackPressedCallback(callback: () -> Unit) {
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                callback()
            }
        }
        addBackPressedCallback(onBackPressedCallback)
    }
}
