package com.android.greenlight.main

import com.android.greenlight.utility.IMvpView

interface IMainView : IMvpView {
    fun showProgressBar()
    fun hideProgressBar()
    fun showDialog(title: String, message: String)

}