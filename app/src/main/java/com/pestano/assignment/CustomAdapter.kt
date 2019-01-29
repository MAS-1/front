package com.pestano.assignment

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class MyCustomAdapter(context: Context): BaseAdapter() {

    private val mContext: Context = context
    private var messages: MutableList<Message> = ArrayList()

    //render each row
    @SuppressLint("ViewHolder", "SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater = LayoutInflater.from(mContext)
        val rowMain = layoutInflater.inflate(R.layout.message_list, parent, false)

        val titleTextView = rowMain.findViewById<TextView>(R.id.title_TextView)
        titleTextView.text = messages[position].content

        val contentTextView = rowMain.findViewById<TextView>(R.id.content_TextView)
        contentTextView.text = "Row number : $position"

        return rowMain
    }

    override fun getItem(position: Int): Any {
        return "TEST STRING"
    }

    //how many rows in the list
    override fun getCount(): Int {
        return messages.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun addItem(m:Message) {
        messages.add(m)
    }
}