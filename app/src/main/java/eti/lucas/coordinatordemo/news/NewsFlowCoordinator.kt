package eti.lucas.coordinatordemo.news

import eti.lucas.coordinatordemo.core.Navigator

class NewsFlowCoordinator(private val navigator: Navigator) {

    fun start() = navigator replaceWith ::NewsFragment

    fun onArticleSelected(articleId: Int) = navigator.push(articleId, fragment = ::ArticleFragment)
}