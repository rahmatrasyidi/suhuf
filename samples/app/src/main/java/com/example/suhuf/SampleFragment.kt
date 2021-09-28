package com.example.suhuf

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.suhuf.sheet.SampleSheet

class SampleFragment : Fragment(R.layout.fragment_sample) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnShow = view.findViewById<Button>(R.id.btnShow)
        btnShow.setOnClickListener { showSheet() }
    }

    private fun showSheet() {
        val bundle = Bundle().apply {
            putString(SampleSheet.KEY_MESSAGE, "Message from arguments!")
        }
        val sheet = SampleSheet().apply {
            arguments = bundle
        }
        sheet.show(this)
    }
}