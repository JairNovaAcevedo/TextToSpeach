package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var txToSpeech: TextToSpeech? = null
    private lateinit var txtStatus: TextView
    private lateinit var edtMensaje: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtStatus = findViewById(R.id.txtStatus)
        edtMensaje = findViewById(R.id.edtMensaje)

        txToSpeech = TextToSpeech(this, this)

        findViewById<Button>(R.id.btnPlay).setOnClickListener{speak()}
    }

    private fun speak(){

        val message = edtMensaje.text.toString()

        if(message.isEmpty()){
            txtStatus.text = getString(R.string.advertencia_campo)
        }
        txToSpeech!!.speak(message,TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onInit(status: Int) {
        if(status== TextToSpeech.SUCCESS){
            txtStatus.text  = getString(R.string.tts_active)
            txToSpeech!!.language = (Locale("ES"))
        }else{
            txtStatus.text  = getString(R.string.tts_no_active)
        }
    }

    override fun onDestroy() {
        if(txToSpeech != null){
            txToSpeech!!.stop()
            txToSpeech!!.shutdown()
        }
        super.onDestroy()
    }
}