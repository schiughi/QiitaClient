package com.schiughi.qiitaclient.client

import com.schiughi.qiitaclient.model.Article
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
* Created by schiughi on 2017/06/01.
*/
interface ArticleClient {

    @GET("/api/v2/items")
    fun search(@Query("query") query: String): Observable<List<Article>>

}