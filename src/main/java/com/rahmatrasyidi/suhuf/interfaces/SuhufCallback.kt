package com.rahmatrasyidi.suhuf.interfaces

import android.view.View

interface SuhufCallback {
    fun onStateChanged(suhuf: View, newState: Int)
    fun onSlide(bottomSheet: View, slideOffset: Float)
}