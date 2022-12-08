package eti.lucas.coordinatordemo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eti.lucas.coordinatordemo.core.Navigator
import eti.lucas.coordinatordemo.core.RootCoordinator
import eti.lucas.coordinatordemo.core.UserManager
import eti.lucas.coordinatordemo.login.LoginCoordinator
import eti.lucas.coordinatordemo.news.NewsCoordinator
import eti.lucas.coordinatordemo.onboarding.OnBoardingCoordinator
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoordinatorModule {
    @Singleton
    @Provides
    fun provideNavigator() = Navigator()

    @Singleton
    @Provides
    fun provideRootCoordinator(
        userManager: UserManager,
        loginCoordinator: LoginCoordinator,
        onBoardingCoordinator: OnBoardingCoordinator,
        newsCoordinator: NewsCoordinator
    ) = RootCoordinator(
        userManager = userManager,
        loginCoordinator,
        onBoardingCoordinator,
        newsCoordinator
    )

    @Singleton
    @Provides
    fun provideLoginCoordinator(navigator: Navigator) = LoginCoordinator(navigator)

    @Singleton
    @Provides
    fun provideOnBoardingCoordinator(navigator: Navigator) = OnBoardingCoordinator(navigator)

    @Singleton
    @Provides
    fun provideNewsCoordinator(navigator: Navigator) = NewsCoordinator(navigator)
}