package com.schiughi.qiitaclient

import android.os.Bundle
import android.view.View
import com.schiughi.qiitaclient.client.ArticleClient
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import com.trello.rxlifecycle.kotlin.bindToLifecycle
import kotlinx.android.synthetic.main.activity_main.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : RxAppCompatActivity() {

    @Inject
    lateinit var articleClient: ArticleClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as QiitaClientApp).component.inject(this)
        setContentView(R.layout.activity_main)

        val listAdapter = ArticleListAdapter(applicationContext)
        list_view.adapter = listAdapter
        // 一覧画面のアイテムがクリックされたとき
        list_view.setOnItemClickListener { adapterView, view, position, id ->
            val intent = ArticleActivity.intent(this , listAdapter.articles[position])
            startActivity(intent)
        }

        //検索ボタンがクリックされたとき
        search_button.setOnClickListener {
            progress_bar.visibility = View.VISIBLE

            articleClient.search(query_edit_text.text.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doAfterTerminate { progress_bar.visibility = View.GONE }
                    .bindToLifecycle(this)
                    .subscribe({
                        query_edit_text.text.clear()
                        listAdapter.articles = it
                        listAdapter.notifyDataSetChanged()
                    },{
                        toast("error: $it")
                    })
        }
    }

}
