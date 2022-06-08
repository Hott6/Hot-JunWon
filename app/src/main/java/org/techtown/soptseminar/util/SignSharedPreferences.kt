package org.techtown.soptseminar.util

import android.content.Context

object SignSharedPreferences {
    private const val GITHUB_SIGN_IN = "my_github_sign_in"
    private const val ID = "id"
    private const val AUTO_MODE = "auto_mode"
    private const val PW = "pw"

    fun setUserId(context: Context, input: String) {
        context.getSharedPreferences(GITHUB_SIGN_IN, Context.MODE_PRIVATE).edit().apply {
            putString(ID, input)
            apply()
        }
    }

    fun setAutoMode(context: Context, boolean: Boolean) {
        context.getSharedPreferences(GITHUB_SIGN_IN, Context.MODE_PRIVATE)
            .edit()
            .putBoolean(AUTO_MODE, boolean)
            .apply()
    }

    fun getAutoMode(context: Context): Boolean {
        return context.getSharedPreferences(GITHUB_SIGN_IN, Context.MODE_PRIVATE)
            .getBoolean(AUTO_MODE, false)
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
