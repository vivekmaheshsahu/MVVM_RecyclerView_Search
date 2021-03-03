package com.android.greenlight.viewmodel.welcome_screen

import com.android.greenlight.common.utility.IBasePresenter

/**
 * @author Vivek & Juilee  Created on 23/11/2020
 */
interface IMainViewModel<V> : IBasePresenter<V> {
    fun initializeDBHelper()
    fun checkPermissions(): Boolean
    fun getPermissions(listPermissionsNeeded: List<String>)
    fun validateCredentials(username: String?, password: String?)
    fun fetchdetails()
    fun createRequestBody()
    fun checkIfUserAlreadyLoggedIn()
    fun deleteUserDetails()
}