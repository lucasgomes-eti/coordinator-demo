package eti.lucas.coordinatordemo.login

import com.jakewharton.rxrelay2.PublishRelay
import eti.lucas.coordinatordemo.core.UserManager
import io.reactivex.Observable

class LoginStateMachine(private val userManager: UserManager) {

    data class LoginCredentials(val username: String, val password: String)

    val input: PublishRelay<LoginCredentials> = PublishRelay.create()

    val state: Observable<State> = input.switchMap { (username, password) ->
        userManager.signIn(username, password)
            .map {
                when(it) {
                    UserManager.SigningResult.SUCCESSFUL -> State.Successful
                    UserManager.SigningResult.ERROR -> State.UnknownUserError
                }
            }
            .startWith(State.Loading)
    }

    sealed class State {
        object Loading : State()
        object UnknownUserError : State()
        object Successful: State()
    }
}