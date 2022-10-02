package eti.lucas.coordinatordemo.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import eti.lucas.coordinatordemo.AuthenticatedUser
import eti.lucas.coordinatordemo.core.UserManager
import io.reactivex.disposables.Disposable


class WelcomeViewModel(
    userManager: UserManager,
    private var onNextClicked: (() -> Unit)?
) : ViewModel() {

    private var _username = MutableLiveData<String>()
    val username: LiveData<String> get() = _username

    private var disposable: Disposable = userManager.currentUser
        .filter { it is AuthenticatedUser }
        .subscribe { _username.value = (it as AuthenticatedUser).username }

    fun onNextClicked(){
        onNextClicked!!()
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
        onNextClicked = null
    }
}