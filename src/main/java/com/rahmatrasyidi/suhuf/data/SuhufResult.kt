package com.rahmatrasyidi.suhuf.data

import android.os.Bundle
import java.io.Serializable

data class SuhufResult(
    val data: Bundle?,
    val resultCode: Int
) : Serializable {

    fun isResultPositive() = resultCode == KEY_POSITIVE_ACTION
    fun isNegativeResult() = resultCode == KEY_NEGATIVE_ACTION
    fun getString(key: String) = data?.getString(key, "")!!
    fun getSerializable(key: String) = data?.getSerializable(key)
    fun getBoolean(key: String) = data?.getBoolean(key, false)
    fun getLong(key: String) = data?.getLong(key, 0L)
    fun getInt(key: String) = data?.getInt(key, 0)
    fun getFloat(key: String) = data?.getFloat(key, 0F)
    fun getParcelableArrayList(key: String): List<Any> = data?.getParcelableArrayList(key) ?: emptyList()
    fun getParcelable(key: String): Any? = data?.getParcelable(key)

    companion object {
        const val KEY_POSITIVE_ACTION = 2000
        const val KEY_NEGATIVE_ACTION = 2001
    }
}