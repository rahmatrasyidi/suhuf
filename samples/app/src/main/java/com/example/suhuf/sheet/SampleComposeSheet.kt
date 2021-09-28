package com.example.suhuf.sheet

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.rahmatrasyidi.suhuf.compose.SuhufCompose
import com.rahmatrasyidi.suhuf.data.PeekHeight

class SampleComposeSheet : SuhufCompose() {

    override val identifier: String get() = IDENTIFIER
    override val isCancellable: Boolean get() = true
    override val isDraggable: Boolean get() = true
    override val peekHeight: PeekHeight get() = PeekHeight.HEIGHT_FULL

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setContent {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                BasicText(
                    text = "Hello world",
                    modifier = Modifier.padding(vertical = Dp(24f)),
                    style = TextStyle(color = Color.Blue, fontSize = 36.sp)
                )
            }
        }
    }

    companion object {
        const val IDENTIFIER = "sampleComposeSheet"
    }
}