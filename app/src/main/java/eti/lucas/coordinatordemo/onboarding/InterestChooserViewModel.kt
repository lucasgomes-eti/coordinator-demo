package eti.lucas.coordinatordemo.onboarding

import androidx.lifecycle.ViewModel
import eti.lucas.coordinatordemo.core.UserManager

class InterestChooserViewModel(
    private val userManager: UserManager,
    private var onNextClicked: (() -> Unit)?
) : ViewModel() {

    fun onNextClicked() {
        userManager.markOnboardingComplete()
        onNextClicked!!()
    }

    override fun onCleared() {
        super.onCleared()
        onNextClicked = null
    }
}