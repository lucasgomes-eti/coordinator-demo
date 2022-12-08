package eti.lucas.coordinatordemo.onboarding

import eti.lucas.coordinatordemo.core.Navigator
import javax.inject.Inject

class OnBoardingCoordinator @Inject constructor(
    private val navigator: Navigator
) {

    var onFinished: (() -> Unit?)? = null

    fun start() = navigator replaceWith ::WelcomeFragment

    fun onWelcomeShown() = navigator replaceWith ::InterestChooserFragment

    fun onPersonalInterestsSelected() = onFinished!!()
}