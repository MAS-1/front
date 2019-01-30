package com.pestano.assignment

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.sql.Timestamp

class WritingActivity : AppCompatActivity() {

    var database = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

    }

    @SuppressLint("PrivateResource")
    fun sendMessage(view: View) {
        val content = findViewById<EditText>(R.id.enter_message_editText)
        val title = findViewById<EditText>(R.id.title_editText)
        val category = findViewById<RadioGroup>(R.id.radioGroup)

        var selected_cat = category.checkedRadioButtonId

        val category_chosen = findViewById<RadioButton>(selected_cat)


        val timestamp = Timestamp(System.currentTimeMillis())
//        timestamp = com.google.firebase.Timestamp(System.currentTimeMillis())


        val messageSent: HashMap<Any, Any?> = HashMap()
        messageSent["title"] = title.text.toString()
        messageSent["content"] = content.text.toString()
        messageSent["timestamp"] = timestamp
        messageSent["author"] = FirebaseAuth.getInstance().currentUser?.displayName
        messageSent["category"] = category_chosen.text.toString()

        database.collection("messages")
            .add(messageSent)
            .addOnSuccessListener { documentReference ->
                System.out.println(
                    "DocumentSnapshot added with ID: " + documentReference.id
                )
            }
            .addOnFailureListener { e -> System.out.println("Error adding document$e") }

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }
}
