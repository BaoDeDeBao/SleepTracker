package com.example.sleeptracker

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_actity.*

class AddActity : AppCompatActivity() {

    private var quality: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_actity)

        imageViewVeryS.setOnClickListener {
            quality = 5
            textViewQuality.text = "Very Happy"
        }

        imageViewS.setOnClickListener {
            quality = 4
            textViewQuality.text = "Happy"
        }

        imageViewN.setOnClickListener {
            quality = 3
            textViewQuality.text = "Neutral"
        }

        imageViewDS.setOnClickListener {
            quality = 2
            textViewQuality.text = "Sad"
        }

        imageViewVeryDS.setOnClickListener {
            quality = 1
            textViewQuality.text = "Very Sad"
        }

        buttonSave.setOnClickListener {
            if(quality == 0) {
                Toast.makeText(applicationContext, "Please indicate sleep quality", Toast.LENGTH_SHORT).show()
                return@setOnClickListener //Stop execution here
            }

            val intent = Intent()
            intent.putExtra(EXTRA_QUALITY, quality)
            setResult(Activity.RESULT_OK, intent) // Pass data back
            finish()
        }

    }

    companion object {
        const val EXTRA_QUALITY = "com.example.sleeptracker.QUALITY"
    }
}
