package com.example.suhuf

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.suhuf.sheet.SampleComposeSheet
import com.example.suhuf.sheet.SampleSheet
import com.rahmatrasyidi.suhuf.data.SheetResult
import com.rahmatrasyidi.suhuf.interfaces.SheetActions

class SampleActivity : AppCompatActivity(), SheetActions {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)

        val btnShow = findViewById<Button>(R.id.btnShow)
        btnShow.setOnClickListener { showSheet() }

        val btnShowCompose = findViewById<Button>(R.id.btnShowCompose)
        btnShowCompose.setOnClickListener { showComposeSheet() }

        supportFragmentManager.beginTransaction().add(R.id.flContainer, SampleFragment()).commit()
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

    private fun showComposeSheet() {
        val sheet = SampleComposeSheet()
        sheet.show(this)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onSheetResult(identifier: String, result: SheetResult) {
        if (identifier == SampleSheet.IDENTIFIER) {
            val message = when {
                result.isResultPositive() -> "Result positive from $identifier"
                result.isResultNegative() -> {
                    result.data?.getString(SampleSheet.KEY_MESSAGE).orEmpty()
                }
                else -> "Result cancel from $identifier"
            }
            showToast(message)
        }
    }
}