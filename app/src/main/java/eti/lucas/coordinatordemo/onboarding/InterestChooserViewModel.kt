package eti.lucas.coordinatordemo.onboarding

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import eti.lucas.coordinatordemo.core.UserManager
import javax.inject.Inject

@HiltViewModel
class InterestChooserViewModel @Inject constructor(
    private val userManager: UserManager,
    private val onboardingCoordinator: OnBoardingCoordinator
) : ViewModel() {

    fun onNextClicked() {
        userManager.markOnBoardingComplete()
        onboardingCoordinator.onPersonalInterestsSelected()
    }
}