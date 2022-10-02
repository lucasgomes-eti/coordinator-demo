package eti.lucas.coordinatordemo.news

import androidx.lifecycle.ViewModel
import eti.lucas.coordinatordemo.core.UserManager

class NewsViewModel(
    private var userManager: UserManager,
    private var onArticleClicked: ((Int) -> Unit)?
): ViewModel() {

    fun onArticleClicked() {
        onArticleClicked!!(1)
    }

    fun onLogoutClicked() {
        userManager.logout().blockingAwait()
    }

    override fun onCleared() {
        super.onCleared()
        onArticleClicked = null
    }
}