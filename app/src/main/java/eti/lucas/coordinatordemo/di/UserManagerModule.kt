package eti.lucas.coordinatordemo.di

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import eti.lucas.coordinatordemo.core.UserManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserManagerModule {
    @Singleton
    @Provides
    fun provideUserManager(preferences: SharedPreferences) = UserManager(preferences)
}