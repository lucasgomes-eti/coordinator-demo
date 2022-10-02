package eti.lucas.coordinatordemo.core

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import eti.lucas.coordinatordemo.R
import eti.lucas.coordinatordemo.navigator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigator.activity = this
    }

    override fun onDestroy() {
        super.onDestroy()
        navigator.activity = null
    }

}