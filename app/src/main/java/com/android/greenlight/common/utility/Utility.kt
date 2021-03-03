package com.android.greenlight.common.utility

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.view.View
import android.widget.ProgressBar
import java.io.ByteArrayOutputStream
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.text.SimpleDateFormat
import java.util.*
import com.android.greenlight.common.database.DatabaseManager


class Utility  {
    val COMMAN_PREF_NAME = "CommonPrefs"
    private var sTheme = 0
    val THEME_DEFAULT = 0
    val THEME_MUW = 1
    val THEME_SION_HOSPITAL = 2
    val THEME_MUW_TEST = 3
    val projectID = "projectIdKey"
    val projectName = "projectNameKey"
    val projectClickedItem = "projectClickedItemKey"
    val Language = "LANGUAGE"

    fun setProjectThemePreference(clickedItem: Int, projName: String?, projID: Int, context: Context) {
        val prefs = context.getSharedPreferences(COMMAN_PREF_NAME, Activity.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putInt(projectClickedItem, clickedItem)
        editor.putInt(projectID, projID)
        editor.putString(projectName, projName)
        editor.commit()
    }

    /**
     * change the language for the app.
     *
     * @param context
     * @param locale
     */
    fun setApplicationLocale(context: Context, locale: String) {
        try {
            setLocaleInPreference(locale, context)

            var res = context.applicationContext.resources
            // Change locale settings in the app.
            var dm = res.displayMetrics
            var conf = res.configuration
            var localeArray = locale.split("_".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if (localeArray.size > 1) {
                conf.locale = Locale(localeArray[0], localeArray[1])
            } else {
                conf.locale = Locale(locale)
            }
            res.updateConfiguration(conf, dm)
        } catch (ex: Exception) {
        }

    }

    fun setLocaleInPreference(locale: String, context: Context) {
        var prefs : SharedPreferences = context.getSharedPreferences(COMMAN_PREF_NAME, Activity.MODE_PRIVATE)
        var editor : SharedPreferences.Editor = prefs.edit()
        editor.putString(Language, locale)
        editor.commit()
    }

    /**
     * @param view         View to animate
     * @param toVisibility Visibility at the end of animation
     * @param toAlpha      Alpha at the end of animation
     * @param duration     Animation duration in ms
     */
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

    fun getCurrentDateTime(): String? {
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(Date())
    }


    fun getDatabase(): SQLiteDatabase {
        return DatabaseManager.getInstance().openDatabase()
    }


    /**
     * This is the method which generates the Hash.
     * @param s = JsonResponse
     * @return hash in string format
     */
    fun mdFive(s: String): String {
        val MD5 = "MD5"
        try {
            // Create MD5 Hash
            val digest = MessageDigest
                    .getInstance(MD5)
            digest.update(s.toByteArray())
            val messageDigest = digest.digest()

            // Create Hex String
            val hexString = StringBuilder()
            for (aMessageDigest in messageDigest) {
                var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
                while (h.length < 2)
                    h = "0$h"
                hexString.append(h)
            }
            return hexString.toString()

        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        return ""
    }

    /*
    fun getUserId(): String {
        val cursor = getDatabase().rawQuery("SELECT " + DatabaseContract.LoginTable.COLUMN_USER_ID
                + " FROM " + DatabaseContract.LoginTable.TABLE_NAME, null)
        return if (cursor.moveToFirst()) cursor.getString(cursor.getColumnIndex(DatabaseContract.LoginTable.COLUMN_USER_ID)) else "0"
    }
*/
    fun getImageByteArray(bitmap: Bitmap): ByteArray? {
        val COMPRESSION_QUALITY = 100
        val byteArrayBitmapStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, COMPRESSION_QUALITY,
                byteArrayBitmapStream)
        return byteArrayBitmapStream.toByteArray()
    }

    fun getAppVersionName(context: Context): String? {
        var pInfo: PackageInfo? = null
        try {
            pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return pInfo!!.versionName
    }

    fun setDataEntryOnce(context: Context) {
        val DataEntry = "data"
        val value = "1"
        val prefs = context.getSharedPreferences(COMMAN_PREF_NAME, Activity.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString(DataEntry, value)
        editor.commit()
    }

    /**
     * Set the theme of the Activity, and restart it by creating a new Activity of the same type.
     */
    fun changeToTheme(activity: Activity, theme: Int) {
        sTheme = theme
        activity.finish()
        activity.startActivity(Intent(activity, activity.javaClass))
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

}