package org.techtown.soptseminar.util

import android.content.Context

object SignSharedPreferences {
    private const val GITHUB_SIGN_IN = "my_github_sign_in"
    private const val ID = "my_id"
    private const val PW = "my_pw"

    fun setUserId(context: Context, input: String) {
        context.getSharedPreferences(GITHUB_SIGN_IN, Context.MODE_PRIVATE).edit().apply {
            putString(ID, input)
            apply()
        }
    }

    fun getUserId(context: Context) =
        context.getSharedPreferences(GITHUB_SIGN_IN, Context.MODE_PRIVATE)
            .getString(ID, "")

    fun setUserPassWord(context: Context, input: String) {
        context.getSharedPreferences(GITHUB_SIGN_IN, Context.MODE_PRIVATE)
            .edit()
            .putString(PW, input)
            .apply()
    }

    fun getUserPassword(context: Context) =
        context.getSharedPreferences(GITHUB_SIGN_IN, Context.MODE_PRIVATE)
            .getString(PW, "")

    fun clearAll(context: Context) {
        context.getSharedPreferences(GITHUB_SIGN_IN, Context.MODE_PRIVATE)
            .edit()
            .clear()
            .apply()
    }
}
