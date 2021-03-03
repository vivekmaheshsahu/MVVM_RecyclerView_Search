package com.android.greenlight.unittestcase

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CheckValuesTest {

    /*
            To check if parameter of function will be empty then
            return will be false of the test case 1 (emptyParameter)
     */
    @Test
    fun emptyParameter() {
        val result = CheckValues.validateInput(
            "", ""
        )
        assertThat(result).isFalse()
    }

    /*
            This testcase is used to check the function if both parameter will be
            available then function will be returning true for test case 2
     */

    @Test
    fun checkValue() {
        val result = CheckValues.validateInput(
            "mumbai", "delhi"
        )
        assertThat(result).isTrue()
    }

/*
            This test case is used to check if one parameter is missing then test case will fail
            and return false value
*/

    @Test
    fun oneParameterMissing() {
        val result = CheckValues.validateInput(
            "mumbai", ""
        )
        assertThat(result).isFalse()
    }

/*
            This test case is used to check if both are string then only pass otherwise
             testcase will be fail and return false value
*/

    @Test
    fun bothNumbers() {
        val result = CheckValues.validateInput(
            "10", "11"
        )
        assertThat(result).isFalse()
    }

    /*
            This test case is used to check if both are string then only pass otherwise
             testcase will be fail and return false value
*/

    @Test
    fun oneNumbers() {
        val result = CheckValues.validateInput(
            "10", ""
        )
        assertThat(result).isFalse()
    }
}