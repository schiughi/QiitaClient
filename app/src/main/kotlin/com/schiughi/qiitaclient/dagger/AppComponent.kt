package com.schiughi.qiitaclient.dagger

import com.schiughi.qiitaclient.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
* Created by schiughi on 2017/06/03.
*/
@Component(modules = arrayOf(ClientModule::class))
@Singleton
interface AppComponent{
    fun inject(mainActivity: MainActivity)
}