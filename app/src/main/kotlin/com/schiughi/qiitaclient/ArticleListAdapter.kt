package com.schiughi.qiitaclient

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.schiughi.qiitaclient.model.Article
import com.schiughi.qiitaclient.view.ArticleView

/**
* Created by schiughi on 2017/05/30.
*/
class ArticleListAdapter(private val context: Context) : BaseAdapter(){
    var articles: List<Article> = emptyList()

    override fun getCount(): Int = articles.size

    override fun getItem(position: Int): Any? = articles[position]

    override fun getItemId(position: Int): Long = 0

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View{
        return (convertView as? ArticleView) ?: ArticleView(context).apply { setArticle(articles[position]) }
    }
}