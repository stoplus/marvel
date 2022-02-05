package com.smart.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.ViewModelParameters
import org.koin.androidx.viewmodel.getViewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.parameter.emptyParametersHolder
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

abstract class BaseFragment<VM : ViewModel, VB : ViewBinding> : Fragment() {
    lateinit var viewModel: VM
    lateinit var binding: VB
    private var contentView: View? = null

    var backPressedCallback: OnBackPressedCallback? = null
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewBindingClass()
        viewModel = getKoin().getViewModel(
            ViewModelParameters(
                clazz = getViewModelKClass(),
                owner = this,
                parameters = getParameters()
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        if (contentView == null) {
            contentView = binding.root
        }
        return contentView
    }

    protected fun <T> LiveData<T>.observe(block: (T) -> Unit) {
        observe(viewLifecycleOwner, { it?.let(block) })
    }

    protected fun <T> LiveData<T>.observeNulls(block: (T?) -> Unit) {
        observe(viewLifecycleOwner, { block(it) })
    }

    fun addBackPressedCallback(callback: OnBackPressedCallback) {
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

    @Suppress("UNCHECKED_CAST")
    private fun getViewModelKClass(): KClass<VM> {
        val actualClass =
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>
        return actualClass.kotlin
    }

    open fun getParameters(): ParametersDefinition = {
        emptyParametersHolder()
    }

    @Suppress("UNCHECKED_CAST")
    private fun initViewBindingClass() {
        val actualClass =
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<VB>

        val method = actualClass.getMethod("inflate", LayoutInflater::class.java)
        binding = method.invoke(null, layoutInflater) as VB
    }
}
