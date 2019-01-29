package com.pestano.assignment


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ListView
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import java.sql.Timestamp



class MainActivity : AppCompatActivity() {

    var db = FirebaseFirestore.getInstance()
    val adapter = MyCustomAdapter(this)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView: ListView = findViewById(R.id.messages_ListView)

        listView.adapter = adapter

        readFromDB()

    }



    @SuppressLint("PrivateResource")
    fun sendMessage(view: View) {


        val editText = findViewById<EditText>(R.id.enter_message_EditText)
        val message = editText.text.toString()
        if(message!="") {
            editText.text = null

            val timestamp = Timestamp(System.currentTimeMillis())
//        timestamp = com.google.firebase.Timestamp(System.currentTimeMillis())


            val messageSent: HashMap<Any, Any?> = HashMap()
            messageSent["title"] = "second message"
            messageSent["content"] = message
            messageSent["timestamp"] = timestamp
            if(FirebaseAuth.getInstance().currentUser!=null ){
                messageSent["author"] = FirebaseAuth.getInstance().currentUser?.displayName

            }
            else {
                messageSent["author"] = FirebaseAuth.getInstance().currentUser?.email!!.split("@")[0]
            }
// Add a new document with a generated ID
            db.collection("messages")
                .add(messageSent)
                .addOnSuccessListener { documentReference ->
                    System.out.println(
                        "DocumentSnapshot added with ID: " + documentReference.id
                    )
                }
                .addOnFailureListener { e -> System.out.println("Error adding document$e") }
        }

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

    fun readFromDB() {
        db.collection("messages").orderBy("timestamp",Query.Direction.ASCENDING).addSnapshotListener( EventListener<QuerySnapshot> { value, e ->
            if (e != null) {
                Log.w("message no existing", "Listen failed.", e)
                return@EventListener
            }

            val cities = ArrayList<String>()
            adapter.notifyDataSetInvalidated()
            for (doc in value!!) {
                if (doc.get("title") != null && doc.get("content") != null) {
                    Log.d("data listener", "Current data: " + value.documents)
                    val m:Message = doc.toObject(Message::class.java)

                    adapter.addItem(m)

                    Log.d("receive data", doc.id + " => " + doc.data + " content : " + doc["content"])
                    Log.d("timestamp of message",""+doc["timestamp"] )
                }
            }
            adapter.notifyDataSetChanged()
            Log.d("message processed", "Current cites in CA: $cities")

        })
    }
}
