package com.pestano.assignment

import android.annotation.SuppressLint
import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.CardView
import android.view.Gravity
import android.view.View
import android.widget.*
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth

import com.firebase.ui.auth.AuthUI

class MainActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
    @SuppressLint("PrivateResource")
    fun sendMessage(view: View) {
        val editText = findViewById<EditText>(R.id.enter_message_edit)
        val message = editText.text.toString()

        val linearLayout = findViewById<LinearLayout>(R.id.linLayout)
        val cv = CardView(this)
        val tv = TextView(this)

        tv.gravity = Gravity.CENTER
        tv.text = message
        tv.textSize = 20F
        tv.setTextColor(Color.WHITE)
        linearLayout.addView(cv)
        val layoutParams = cv.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.setMargins(0, 8, 0, 8)
        cv.requestLayout()

        cv.addView(tv)
        cv.setBackgroundResource(R.color.colorPrimary)

        editText.text = null


    }
    fun signOut(view: View) {
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                // user is now signed out
                startActivity(Intent(this, FirebaseUIActivity::class.java))
                finish()
            }
    }
}
