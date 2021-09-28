package com.rahmatrasyidi.suhuf.interfaces

import com.rahmatrasyidi.suhuf.data.SuhufResult

interface SuhufActions {
    fun onSheetResult(identifier: String, result: SuhufResult)
}