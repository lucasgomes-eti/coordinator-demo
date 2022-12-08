package eti.lucas.coordinatordemo.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import eti.lucas.coordinatordemo.R
import eti.lucas.coordinatordemo.core.FragmentWithParams
import kotlin.properties.Delegates.notNull

class ArticleFragment : Fragment(), FragmentWithParams {
    private var articleId by notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            articleId = it.getInt(ARG_ARTICLE_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(articleId: Int) = ArticleFragment().apply {
            arguments = bundleOf(ARG_ARTICLE_ID to articleId)
        }

        const val ARG_ARTICLE_ID = "article_id"
    }

    override fun newInstance(params: Bundle): Fragment {
        return ArticleFragment().apply { arguments = params }
    }
}