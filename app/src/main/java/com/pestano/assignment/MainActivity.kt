package com.pestano.assignment

import android.app.ActionBar
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TableRow
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun sendMessage(view: View) {
        val editText = findViewById<EditText>(R.id.editText)
        val message = editText.text.toString()
        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
//        val textView = findViewById<TextView>(R.id.textViewMain).apply {
//            text = message
//        }
//        val lparams = ViewGroup.LayoutParams(
//            TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT
//        )
//
//        val tv = TextView(this)
//        tv.layoutParams = lparams
//        tv.text = "test"
//        this.addContentView(tv,lparams)
    }
}
