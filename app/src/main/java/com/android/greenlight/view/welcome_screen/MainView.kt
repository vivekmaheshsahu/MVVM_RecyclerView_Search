package com.android.greenlight.view.welcome_screen

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.android.greenlight.R
import com.android.greenlight.common.utility.Utility
import com.android.greenlight.view.zone.ZoneActivity
import com.android.greenlight.viewmodel.welcome_screen.MainViewModel

class MainView : AppCompatActivity(), IMainView {

    val uti = Utility()
    var progressOverlay: ProgressBar? = null
    var mainViewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var tex: TextView = findViewById(R.id.toolbar_title)
        tex.setText("Metrics")
        mainViewModel = MainViewModel()
        mainViewModel?.attachView(this)
      //  mainViewModel?.fetchdetails()
    }

    fun click(view: View?) {
        intent = Intent(this, ZoneActivity::class.java)
        startActivity(intent)
    }

    override fun showProgressBar() {
        uti.animateView(progressOverlay, View.VISIBLE, 0.4f, 200)
    }

    override fun hideProgressBar() {
        progressOverlay?.let { uti.animateView(it, View.GONE, 0.4f, 200) }
    }

    override fun getContext(): Context {
        return this;
    }

    override fun showDialog(title: String, message: String) {
        val builder: AlertDialog.Builder
        builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert)
        } else {
            AlertDialog.Builder(context)
        }
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.yes) { dialog, which ->
                    // continue with delete
                }
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
    }

    override fun onPostResume() {
        super.onPostResume()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null) {
            if (mainViewModel!!.checkPermissions())
                mainViewModel?.attachView(this)
           }

    }

}