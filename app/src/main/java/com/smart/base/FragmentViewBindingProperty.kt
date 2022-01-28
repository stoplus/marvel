package com.smart.base

import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

private class FragmentViewBindingProperty<F : Fragment, T : ViewBinding>(
    viewBinder: (F) -> T
) : ViewBindingProperty<F, T>(viewBinder) {

    override fun getLifecycleOwner(thisRef: F) = thisRef.viewLifecycleOwner
}

/**
 * Создаёт проперти с новым биндингом [T] для фрагмента [F]
 *
 * @param viewBinder Создаёт биндинг из [F]
 */
@JvmName("viewBindingFragment")
fun <F : Fragment, T : ViewBinding> Fragment.viewBinding(viewBinder: (F) -> T): ViewBindingProperty<F, T> {
    return FragmentViewBindingProperty(viewBinder)
}

/**
 * Создаёт проперти с новым биндингом [T] для фрагмента [F]
 *
 * @param viewBindingFactory Создаёт биндинг из [View]
 * @param viewProvider Получает [View] из фрагмента. По умолчанию использует [Fragment.requireView]
 */
@JvmName("viewBindingFragment")
inline fun <F : Fragment, T : ViewBinding> Fragment.viewBinding(
    crossinline viewBindingFactory: (View) -> T,
    crossinline viewProvider: (F) -> View = Fragment::requireView
): ViewBindingProperty<F, T> {
    return viewBinding { fragment: F -> viewBindingFactory(viewProvider(fragment)) }
}