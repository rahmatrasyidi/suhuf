package com.example.suhuf.sheet

import com.example.suhuf.R
import com.rahmatrasyidi.suhuf.Suhuf
import com.rahmatrasyidi.suhuf.data.PeekHeight

class SampleSheet : Suhuf(R.layout.sheet_sample) {

    override val identifier: String get() = IDENTIFIER
    override val isCancellable: Boolean get() = true
    override val isDraggable: Boolean get() = true
    override val peekHeight: PeekHeight get() = PeekHeight.HEIGHT_FULL

    companion object {
        const val IDENTIFIER = "sampleSheet"
    }
}