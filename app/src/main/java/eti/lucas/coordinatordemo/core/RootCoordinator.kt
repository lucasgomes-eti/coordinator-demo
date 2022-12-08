package eti.lucas.coordinatordemo.core

import eti.lucas.coordinatordemo.AuthenticatedUser
import eti.lucas.coordinatordemo.NotAuthenticated
import eti.lucas.coordinatordemo.login.LoginCoordinator
import eti.lucas.coordinatordemo.news.NewsCoordinator
import eti.lucas.coordinatordemo.onboarding.OnBoardingCoordinator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class RootCoordinator @Inject constructor(
    private val userManager: UserManager,
    private val loginCoordinator: LoginCoordinator,
    private val onBoardingCoordinator: OnBoardingCoordinator,
    private val newsCoordinator: NewsCoordinator
) {

    init {
        onBoardingCoordinator.onFinished = ::onBoardingCompleted
    }

    fun start(): Disposable = userManager.currentUser.observeOn(AndroidSchedulers.mainThread()).subscribe {
        when (it) {
            is NotAuthenticated -> loginCoordinator.start()
            is AuthenticatedUser -> if (it.onboardingCompleted) newsCoordinator.start() else onBoardingCoordinator.start()
            else -> throw IllegalStateException()
        }
    }

    private fun onBoardingCompleted() {
        newsCoordinator.start()
    }
}