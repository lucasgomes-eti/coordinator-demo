package eti.lucas.coordinatordemo.login

import eti.lucas.coordinatordemo.core.Navigator

class LoginCoordinator(private val navigator: Navigator) {

    fun start() = navigator replaceWith ::LoginFragment

    fun registerNewUser() = navigator push ::RegistrationFragment

    fun forgotPassword() = navigator push ::RecoverPasswordFragment
}