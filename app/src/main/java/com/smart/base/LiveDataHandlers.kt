package com.smart.base

import androidx.annotation.MainThread
import androidx.annotation.Nullable
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import timber.log.Timber
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Create
 * val click : LiveData<Event<String>>
 *
 * Only proceed if the event has never been handled
 * click.observe(this, Observer {
 *     it.take()?.let {}
 * })
 *
 * Trigger the event by setting a new Event as a new value
 * click.value = Event(itemId)
 * */
open class Event<out T>(private val content: T) {
    var handled = false
        private set

    fun take() = if (handled) {
        null
    } else {
        handled = true
        content
    }

    fun reset() {
        handled = false
    }

    fun peek(): T = content

    fun poll(): T? = content.takeUnless { handled }

    override fun toString() = "Event: ${content.toString()} [handled: $handled]"
}

/**
 * val click = SingleLiveEvent<Any>()
 *
 * click.observe(this, Observer {})
 *
 * click.call()
 * */
class SingleLiveEvent<T> : MutableLiveData<T>() {

    private val mPending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasActiveObservers()) {
            Timber.tag(TAG).w("Multiple observers registered but only one will be notified of changes.")
        }

        super.observe(owner, Observer<T> { t ->
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })
    }

    @MainThread
    override fun setValue(@Nullable t: T?) {
        mPending.set(true)
        super.setValue(t)
    }

    @MainThread
    fun call() {
        value = null
    }

    companion object {
        private val TAG = "SingleLiveEvent"
    }
}


/**
 * как #SingleLiveEvent, но работает для нескольких подписчиков.
 * Данные не передаются до подписчиков, в случае когда не осталось активных подписчиков (например смена конфигурации)
 * вплоть до нового испускания элементов
 * */
class SingleEventLiveData<T> : MutableLiveData<T>() {

    private val isEmitAllowed = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, {
            if (isEmitAllowed.get()) observer.onChanged(it)
        })
    }

    @MainThread
    override fun setValue(@Nullable t: T?) {
        isEmitAllowed.set(true)
        super.setValue(t)
    }

    override fun onInactive() {
        isEmitAllowed.compareAndSet(true, false)
        super.onInactive()
    }
}

fun SingleEventLiveData<Unit>.call() {
    this.value = Unit
}