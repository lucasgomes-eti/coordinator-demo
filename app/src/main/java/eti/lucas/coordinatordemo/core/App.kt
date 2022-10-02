package eti.lucas.coordinatordemo.core

import android.app.Application

class App : Application() {

    val viewModelFactory by lazy { AppViewModelFactory(this) }
}