package eti.lucas.coordinatordemo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import eti.lucas.coordinatordemo.core.UserManager
import eti.lucas.coordinatordemo.login.LoginStateMachine
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object StateMachineModule {
    @Provides
    fun provideLoginStateMachine(userManager: UserManager) = LoginStateMachine(userManager)
}