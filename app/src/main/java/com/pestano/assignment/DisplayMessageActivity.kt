package com.pestano.assignment
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.TextView
class DisplayMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)
        //val listMessage: List<Message>
        // Get the Intent that started this activity and extract the string
        val message = intent.getStringExtra(EXTRA_MESSAGE)

        // Capture the layout's TextView and set the string as its text
        //val textView = findViewById<TextView>(R.id.textView).apply {
        //    text = message
        //}
        //databaseReference = FirebasaDatabase.getInstance().getReferance( m: "message_sent");

    }
//    private fun onStart() {
//        super.onStart();
//        databaseReference.advValueEventListener(new ValueEventListener()){
//            public fun onDataChange(DataSnapshop: dataSnapshot){
//                  for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()){
//                      Message message = messageSnapshot.getValue(Message.message_sent);
//                      messageList.add(message_sent);
//                  }
//                  MessageInfoAdapter messageInfoAdapter = new StudentInfoAdapter (context: DataRetrieved.this, studentList);
//                  listView.setAdapter(studentInfoAdapter);
//            }
//            public fun onCancelled(DatabaseError: databaseError){
//
//            }
//        }
//    }
}
