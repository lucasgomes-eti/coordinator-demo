package eti.lucas.coordinatordemo.core

import android.content.SharedPreferences
import com.jakewharton.rxrelay2.BehaviorRelay
import eti.lucas.coordinatordemo.AuthenticatedUser
import eti.lucas.coordinatordemo.NotAuthenticated
import eti.lucas.coordinatordemo.User
import io.reactivex.Completable
import io.reactivex.Observable

class UserManager(private val sharedPreferences: SharedPreferences) {

    companion object {
        private const val KEY_CURRENT_USER = "current_user"
        private const val KEY_ONBOARDING_COMPLETED = "onboarding_completed"
    }

    private val usernamePasswordMap = HashMap<String, String>()
    private val userRelay = BehaviorRelay.create<User>()
    val currentUser: Observable<User> = userRelay

    init {
        usernamePasswordMap["su"] = "123"
        val current = sharedPreferences.getString(KEY_CURRENT_USER, null)
        userRelay.accept(
            if (current != null)
                AuthenticatedUser(
                    current,
                    sharedPreferences.getBoolean(KEY_ONBOARDING_COMPLETED, false)
                )
            else NotAuthenticated
        )
    }

    fun signIn(username: String, password: String) = Observable.fromCallable {
        Thread.sleep(1_000)
        when (usernamePasswordMap[username]) {
            null -> SigningResult.ERROR
            password -> {
                sharedPreferences.edit().putString(KEY_CURRENT_USER, username).apply()
                userRelay.accept(AuthenticatedUser(username, false))
                SigningResult.SUCCESSFUL
            }
            else -> SigningResult.ERROR
        }
    }

    fun logout() = Completable.fromCallable {
        sharedPreferences.edit().clear().apply()
        userRelay.accept(NotAuthenticated)
    }

    fun markOnBoardingComplete() {
        sharedPreferences.edit().putBoolean(KEY_ONBOARDING_COMPLETED, true).apply()
    }

    enum class SigningResult {
        SUCCESSFUL, ERROR
    }
}