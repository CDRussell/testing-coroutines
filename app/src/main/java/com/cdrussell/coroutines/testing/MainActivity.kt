package com.cdrussell.coroutines.testing

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var heavyWorker: HeavyWorker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        heavyWorker = HeavyWorker()

        textView = findViewById(R.id.status)
        findViewById<Button>(R.id.heavySuspendFunction).setOnClickListener {
            textView.setText(R.string.calculating)

            GlobalScope.launch {
                val result = heavyWorker.heavyOperation()

                withContext(Dispatchers.Main) {
                    textView.text = getString(R.string.result, result.toString())
                }
            }
        }

    }
}
