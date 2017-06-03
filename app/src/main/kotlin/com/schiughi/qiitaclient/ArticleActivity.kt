package com.schiughi.qiitaclient

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.schiughi.qiitaclient.model.Article
import kotlinx.android.synthetic.main.activity_article.*

/**
* Created by schiughi on 2017/06/01.
*/
class ArticleActivity : AppCompatActivity(){
    companion object {
        private const val ARTICLE_EXTRA: String = "article"

        fun intent(context: Context, article: Article): Intent =
                Intent(context , ArticleActivity::class.java).putExtra(ARTICLE_EXTRA,article)
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        val article: Article = intent.getParcelableExtra(ARTICLE_EXTRA)
        article_view.setArticle(article)
        web_view.loadUrl(article.url)
    }
}