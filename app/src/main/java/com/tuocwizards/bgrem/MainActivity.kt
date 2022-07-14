package com.tuocwizards.bgrem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page)

        val button : ImageButton = findViewById(R.id.photo_button)
        button.setOnClickListener {
            val bottomSheetDialog: BottomSheetDialog = BottomSheetDialog(this)
            val bottomSheetView: View = layoutInflater
                .inflate(R.layout.bottom_dialog_window, findViewById(R.id.bottom_dialog_container))

            bottomSheetView.findViewById<Button>(R.id.cancel_button).setOnClickListener{
                Toast.makeText(
                    this, "tttt", Toast.LENGTH_SHORT
                ).show()
            }
            bottomSheetDialog.setContentView(bottomSheetView)
            bottomSheetDialog.show()
        }

    }
}