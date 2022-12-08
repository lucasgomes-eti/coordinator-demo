package eti.lucas.coordinatordemo.news

import androidx.core.os.bundleOf
import eti.lucas.coordinatordemo.core.Navigator

class NewsCoordinator(private val navigator: Navigator) {

    fun start() = navigator replaceWith ::NewsFragment

    fun onArticleSelected(articleId: Int) = navigator.push(bundleOf(ArticleFragment.ARG_ARTICLE_ID to articleId), fragment = ::ArticleFragment)
}