package com.example.suhuf.sheet

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.suhuf.R
import com.rahmatrasyidi.suhuf.Suhuf
import com.rahmatrasyidi.suhuf.data.PeekHeight

class SampleSheet : Suhuf(R.layout.sheet_sample) {

    override val identifier: String get() = IDENTIFIER
    override val isCancellable: Boolean get() = true
    override val isDraggable: Boolean get() = true
    override val peekHeight: PeekHeight get() = PeekHeight.HEIGHT_FULL

    override fun extractArguments(bundle: Bundle?) {
        super.extractArguments(bundle)
        val message = bundle?.getString(KEY_MESSAGE).orEmpty()
        showToast(message)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnPositive = view.findViewById<Button>(R.id.btnPositive)
        btnPositive.setPositiveButtonClicked()

        val btnNegative = view.findViewById<Button>(R.id.btnNegative)
        val bundle = Bundle().apply {
            putString(KEY_MESSAGE, "Message from bundle!")
        }
        btnNegative.setNegativeButtonClicked(bundle)
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val IDENTIFIER = "sampleSheet"

        const val KEY_MESSAGE = "message"
    }
}