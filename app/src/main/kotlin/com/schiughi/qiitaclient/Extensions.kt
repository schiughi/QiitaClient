package com.schiughi.qiitaclient

import android.content.Context
import android.widget.Toast

/**
* Created by schiughi on 2017/06/02.
*/

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT){
        Toast.makeText(this , message , duration).show()
}
