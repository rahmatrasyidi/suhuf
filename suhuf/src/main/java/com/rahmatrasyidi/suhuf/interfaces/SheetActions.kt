package com.rahmatrasyidi.suhuf.interfaces

import com.rahmatrasyidi.suhuf.data.SheetResult

interface SheetActions {
    fun onSheetResult(identifier: String, result: SheetResult)
}