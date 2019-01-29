package com.pestano.assignment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class MyCustomAdapter(context: Context): BaseAdapter() {

    private val mContext: Context = context

    private val names = arrayListOf<String>(
        "Donald Trump", "Steve Jobs", "Tim Cook"
    )

    //render each row
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater = LayoutInflater.from(mContext)
        val rowMain = layoutInflater.inflate(R.layout.message_list, parent, false)

        val titleTextView = rowMain.findViewById<TextView>(R.id.title_TextView)
        titleTextView.text = names.get(position)

        val contentTextView = rowMain.findViewById<TextView>(R.id.content_TextView)
        contentTextView.text = "Row number : $position"

        return rowMain
    }

    override fun getItem(position: Int): Any {
        return "TEST STRING"
    }

    //how many rows in the list
    override fun getCount(): Int {
        return names.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}