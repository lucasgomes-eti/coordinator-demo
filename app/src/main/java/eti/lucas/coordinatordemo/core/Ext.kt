package eti.lucas.coordinatordemo.core

import androidx.lifecycle.*

inline fun <T> LiveData<T>.subscribe(owner: LifecycleOwner, crossinline subscriber: (T) -> Unit) {
    observe(owner, Observer { value -> subscriber(value!!) })
}