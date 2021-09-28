package com.rahmatrasyidi.suhuf.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import com.rahmatrasyidi.suhuf.Suhuf

abstract class SuhufCompose : Suhuf(R.layout.suhuf_compose) {

    fun setContent(content: @Composable () -> Unit) {
        val composeView = requireView().findViewById<ComposeView>(R.id.cvContainer)
        composeView.setContent { content.invoke() }
    }
}