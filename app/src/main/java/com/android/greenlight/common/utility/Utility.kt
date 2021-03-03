package com.android.greenlight.common.utility

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.net.ConnectivityManager
import android.view.View
import android.widget.ProgressBar
import com.android.greenlight.common.database.DatabaseManager

class Utility {

    val COMMAN_PREF_NAME = "CommonPrefs"
    private var sTheme = 0

    fun getDatabase(): SQLiteDatabase {
        return DatabaseManager.getInstance().openDatabase()
    }

    fun hasInternetConnectivity(context: Context): Boolean {
        var rc = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        try {
            val activeNetworkInfo = cm.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable && activeNetworkInfo.isConnected) {
                rc = true
            }
        } catch (e: Exception) {
        }
        return rc
    }
    fun animateView(view: ProgressBar?, toVisibility: Int, toAlpha: Float, duration: Int) {
        val show = toVisibility == View.VISIBLE
        if (show) {
            view?.alpha = 0f
        }
        view?.visibility = View.VISIBLE
        view?.animate()
                ?.setDuration(duration.toLong())
                ?.alpha(if (show) toAlpha else 0F)
                ?.setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        view.visibility = toVisibility
                    }
                })
    }

}