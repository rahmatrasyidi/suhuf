package com.rahmatrasyidi.suhuf.interfaces

import com.rahmatrasyidi.suhuf.data.PeekHeight

interface SheetBehaviour {
    val identifier: String
    val peekHeight: PeekHeight
    val isCancellable: Boolean
    val isDraggable: Boolean
}