package eti.lucas.coordinatordemo.onboarding

import eti.lucas.coordinatordemo.core.Navigator

class OnboardingFlowCoordinator(
    private val navigator: Navigator,
    private val onboardingFinished: () -> Unit
) {

    fun start() = navigator replaceWith ::WelcomeFragment

    fun onWelcomeShown() = navigator replaceWith ::InterestChooserFragment

    fun onPersonalInterestsSelected() {
        onboardingFinished()
    }
}