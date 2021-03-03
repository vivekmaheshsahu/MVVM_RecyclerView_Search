package com.android.greenlight.unittestcase

object CheckValues {

    val zone = listOf("Mumbai","Delhi")


    /**
     * The input is not valid if....
     * ... the data is empty
     * ... all was integer value
     * ... one string one integer
     *
     * The input is valid if
     * ... If all are string value
     * ... More then one value is available
     */


    fun validateInput(
            zone1 : String,
            zone2 : String
    ):Boolean{return true}
}