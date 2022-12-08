package eti.lucas.coordinatordemo.news

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import eti.lucas.coordinatordemo.core.UserManager
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val userManager: UserManager,
    private val newsCoordinator: NewsCoordinator
): ViewModel() {

    fun onArticleClicked() = newsCoordinator.onArticleSelected(1)

    fun onLogoutClicked() {
        userManager.logout().blockingAwait()
    }
}