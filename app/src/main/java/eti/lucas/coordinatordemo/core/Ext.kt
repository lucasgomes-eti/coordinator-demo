package eti.lucas.coordinatordemo

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import eti.lucas.coordinatordemo.core.App
import eti.lucas.coordinatordemo.core.Navigator

val Fragment.application
    get() = requireActivity().application as App

val Activity.navigator: Navigator
    get() = (application as App).viewModelFactory.navigator

inline fun <reified VM : ViewModel> Fragment.getViewModel(): VM {
    return ViewModelProvider(this, application.viewModelFactory)[VM::class.java]
}

inline fun <T> LiveData<T>.subscribe(owner: LifecycleOwner, crossinline subscriber: (T) -> Unit) {
    observe(owner, Observer { value -> subscriber(value!!) })
}