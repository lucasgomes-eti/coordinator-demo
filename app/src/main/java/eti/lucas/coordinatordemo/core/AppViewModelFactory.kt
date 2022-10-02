package eti.lucas.coordinatordemo.core

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import eti.lucas.coordinatordemo.login.LoginFlowCoordinator
import eti.lucas.coordinatordemo.login.LoginStateMachine
import eti.lucas.coordinatordemo.login.LoginViewModel
import eti.lucas.coordinatordemo.news.NewsFlowCoordinator
import eti.lucas.coordinatordemo.news.NewsViewModel
import eti.lucas.coordinatordemo.onboarding.InterestChooserViewModel
import eti.lucas.coordinatordemo.onboarding.OnboardingFlowCoordinator
import eti.lucas.coordinatordemo.onboarding.WelcomeViewModel

class AppViewModelFactory(application: Application): ViewModelProvider.Factory {

    private val userManager = UserManager(application.getSharedPreferences("UserManager", Context.MODE_PRIVATE))

    val navigator = Navigator()

    private val rootCoordinator = RootFlowCoordinator(userManager)
    private val onboardingCoordinator = OnboardingFlowCoordinator(
        navigator = navigator,
        onboardingFinished = rootCoordinator::onboardingCompleted
    )
    private val newsCoordinator = NewsFlowCoordinator(navigator)
    private val loginCoordinator = LoginFlowCoordinator(navigator)

    init {
        rootCoordinator.onboardingFlowCoordinator = onboardingCoordinator
        rootCoordinator.loginFlowCoordinator = loginCoordinator
        rootCoordinator.newsFlowCoordinator = newsCoordinator
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T = when(modelClass) {
        LoginViewModel::class.java -> LoginViewModel(
            loginStateMachine = LoginStateMachine(userManager),
            onForgotPassword = loginCoordinator::forgotPassword,
            onSignUpClicked = loginCoordinator::registerNewUser
        )
        WelcomeViewModel::class.java -> WelcomeViewModel(
            userManager = userManager,
            onNextClicked = onboardingCoordinator::onWelcomeShown
        )
        InterestChooserViewModel::class.java -> InterestChooserViewModel(
            userManager = userManager,
            onNextClicked = onboardingCoordinator::onPersonalInterestsSelected
        )
        NewsViewModel::class.java -> NewsViewModel(
            userManager = userManager,
            onArticleClicked = newsCoordinator::onArticleSelected
        )
        else -> throw IllegalArgumentException("No ViewModel registered for $modelClass")
    } as T
}