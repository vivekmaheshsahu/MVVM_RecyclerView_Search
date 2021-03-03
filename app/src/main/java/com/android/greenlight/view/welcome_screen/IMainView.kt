package com.android.greenlight.view.welcome_screen

import com.android.greenlight.common.utility.IMvpView

interface IMainView : IMvpView {
    fun showProgressBar()
    fun hideProgressBar()
    fun showDialog(title: String, message: String)

}