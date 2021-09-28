package com.example.suhuf

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.suhuf.sheet.SampleComposeSheet
import com.example.suhuf.sheet.SampleSheet

class SampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)

        val btnShow = findViewById<Button>(R.id.btnShow)
        btnShow.setOnClickListener { showSheet() }

        val btnShowCompose = findViewById<Button>(R.id.btnShowCompose)
        btnShowCompose.setOnClickListener { showComposeSheet() }
    }

    private fun showSheet() {
        val sheet = SampleSheet()
        sheet.show(supportFragmentManager, sheet.identifier)
    }

    private fun showComposeSheet() {
        val sheet = SampleComposeSheet()
        sheet.show(supportFragmentManager, sheet.identifier)
    }
}