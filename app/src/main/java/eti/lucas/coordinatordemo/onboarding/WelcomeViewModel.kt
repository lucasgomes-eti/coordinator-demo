package eti.lucas.coordinatordemo.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import eti.lucas.coordinatordemo.AuthenticatedUser
import eti.lucas.coordinatordemo.core.UserManager
import io.reactivex.disposables.Disposable
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    userManager: UserManager,
    private val onboardingCoordinator: OnBoardingCoordinator
) : ViewModel() {

    private var _username = MutableLiveData<String>()
    val username: LiveData<String> get() = _username

    private var disposable: Disposable = userManager.currentUser
        .filter { it is AuthenticatedUser }
        .subscribe { _username.value = (it as AuthenticatedUser).username }

    fun onNextClicked() = onboardingCoordinator.onWelcomeShown()

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}