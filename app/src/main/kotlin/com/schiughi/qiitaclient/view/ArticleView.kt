package com.schiughi.qiitaclient.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.schiughi.qiitaclient.R
import com.schiughi.qiitaclient.model.Article
import kotlinx.android.synthetic.main.view_article.view.*

/**
* Created by schiughi on 2017/05/28.
*/
class ArticleView : FrameLayout{
    constructor(context: Context?) : super(context)
    constructor(context: Context?,
                attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?,
                attrs: AttributeSet?,
                defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?,
                attrs: AttributeSet?,
                defStyleAttr: Int,
                defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    init{
        LayoutInflater.from(context).inflate(R.layout.view_article, this)
    }

    fun setArticle(article: Article){
        title_text_view.text = article.title
        user_name_text_view.text = article.userName()
        Glide.with(context).load(article.userProfileImageUrl()).into(profile_image_view)
    }
}