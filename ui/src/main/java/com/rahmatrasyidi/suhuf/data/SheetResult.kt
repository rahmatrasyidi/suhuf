package com.rahmatrasyidi.suhuf.data

import android.os.Bundle
import java.io.Serializable

data class SheetResult(
    val data: Bundle? = null,
    val resultCode: Int = RESULT_CODE_CANCELLED
) : Serializable {

    fun isResultPositive() = resultCode == RESULT_CODE_POSITIVE
    fun isResultNegative() = resultCode == RESULT_CODE_NEGATIVE

    fun getString(key: String) = data?.getString(key, "")!!

    fun getSerializable(key: String) = data?.getSerializable(key)

    fun getBoolean(key: String) = data?.getBoolean(key, false)

    fun getLong(key: String) = data?.getLong(key, 0L)

    fun getInt(key: String) = data?.getInt(key, 0)

    fun getFloat(key: String) = data?.getFloat(key, 0F)

    fun <T> getParcelableArrayList(key: String): List<T> {
        return data?.getParcelableArrayList(key) ?: emptyList()
    }

    fun <T> getParcelable(key: String): T? = data?.getParcelable(key)

    companion object {
        const val RESULT_CODE_CANCELLED = -1
        const val RESULT_CODE_POSITIVE = 0
        const val RESULT_CODE_NEGATIVE = 1
    }
}