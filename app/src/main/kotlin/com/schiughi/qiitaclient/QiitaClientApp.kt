package com.schiughi.qiitaclient

import android.app.Application
import com.schiughi.qiitaclient.dagger.AppComponent
import com.schiughi.qiitaclient.dagger.DaggerAppComponent
/**
* Created by schiughi on 2017/06/03.
*/
class QiitaClientApp : Application(){
    val component: AppComponent by lazy {
        DaggerAppComponent.create()
    }
}