package com.pestano.assignment

import android.annotation.SuppressLint
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.CardView
import android.view.Gravity
import android.view.View
import android.widget.*
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


import com.firebase.ui.auth.AuthUI




class MainActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    @SuppressLint("PrivateResource")
    fun sendMessage(view: View) {
        val editText = findViewById<EditText>(R.id.editText)
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
        cv.setBackgroundColor(resources.getColor(R.color.background_floating_material_dark))

    }
}
