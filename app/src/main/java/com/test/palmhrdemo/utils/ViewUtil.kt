package com.test.palmhrdemo.utils

import android.view.View

object ViewUtil {

    fun View.show() {
        visibility = View.VISIBLE
    }

    fun View.gone() {
        visibility = View.GONE
    }
}