package eti.lucas.coordinatordemo.core

import eti.lucas.coordinatordemo.AuthenticatedUser
import eti.lucas.coordinatordemo.NotAuthenticated
import eti.lucas.coordinatordemo.login.LoginFlowCoordinator
import eti.lucas.coordinatordemo.news.NewsFlowCoordinator
import eti.lucas.coordinatordemo.onboarding.OnboardingFlowCoordinator
import io.reactivex.android.schedulers.AndroidSchedulers

class RootFlowCoordinator(userManager: UserManager) {

    lateinit var loginFlowCoordinator: LoginFlowCoordinator
    lateinit var onboardingFlowCoordinator: OnboardingFlowCoordinator
    lateinit var newsFlowCoordinator: NewsFlowCoordinator

    init {
        userManager.currentUser.observeOn(AndroidSchedulers.mainThread()).subscribe {
            when (it) {
                is NotAuthenticated -> loginFlowCoordinator.start()
                is AuthenticatedUser -> if (it.onboardingCompleted)
                    newsFlowCoordinator.start() else onboardingFlowCoordinator.start()
                else -> throw IllegalStateException()
            }
        }
    }

    fun onboardingCompleted() {
        newsFlowCoordinator.start()
    }
}