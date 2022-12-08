package eti.lucas.coordinatordemo.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginStateMachine: LoginStateMachine,
    private val loginCoordinator: LoginCoordinator
) : ViewModel() {

    val state: MutableLiveData<LoginViewState> = MutableLiveData()

    private val disposables = CompositeDisposable()

    init {
        val sharedLoginState = loginStateMachine.state
            .subscribeOn(Schedulers.io())
            .share()
            .observeOn(AndroidSchedulers.mainThread())

        disposables.add(sharedLoginState
            .filter { it != LoginStateMachine.State.Successful }
            .map {
                when (it) {
                    LoginStateMachine.State.Loading -> LoginViewState.LoadingState
                    LoginStateMachine.State.UnknownUserError -> LoginViewState.ShowLoginFormWithErrorState
                    else -> throw IllegalStateException("$it should never be reached")
                }
            }
            .startWith(LoginViewState.ShowLoginForm)
            .subscribe {
                state.value = it
            })
    }

    fun login(loginCredentials: LoginStateMachine.LoginCredentials) =
        loginStateMachine.input.accept(loginCredentials)

    fun signUp() = loginCoordinator.registerNewUser()

    fun forgotPassword() = loginCoordinator.forgotPassword()

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    sealed class LoginViewState {
        object ShowLoginForm : LoginViewState()
        object LoadingState : LoginViewState()
        object ShowLoginFormWithErrorState : LoginViewState()
    }
}