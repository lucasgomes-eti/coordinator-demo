package eti.lucas.coordinatordemo.core

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import eti.lucas.coordinatordemo.R
import eti.lucas.coordinatordemo.login.LoginCoordinator
import eti.lucas.coordinatordemo.news.NewsCoordinator
import eti.lucas.coordinatordemo.onboarding.OnBoardingCoordinator
import io.reactivex.disposables.Disposable
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var preferences: SharedPreferences

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var rootCoordinator: RootCoordinator

    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigator.activity = this
        disposable = rootCoordinator.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        navigator.activity = null
        disposable?.dispose()
    }

}