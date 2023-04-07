package edu.skku.cs.pa1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class wordListAdapter(val context:Context, val data: ArrayList<wordList>): BaseAdapter()  {
    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(p0: Int): Any {
        return data[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val generatedView = inflater.inflate(R.layout.word,null)

        val text1 = generatedView.findViewById<TextView>(R.id.firstText)
        val text2 = generatedView.findViewById<TextView>(R.id.secondText)
        val text3 = generatedView.findViewById<TextView>(R.id.thirdText)
        val text4 = generatedView.findViewById<TextView>(R.id.fourText)
        val text5 = generatedView.findViewById<TextView>(R.id.fiveText)

        text1.text = data[p0].word.get(0).toString()
        text2.text = data[p0].word.get(1).toString()
        text3.text = data[p0].word.get(2).toString()
        text4.text = data[p0].word.get(3).toString()
        text5.text = data[p0].word.get(4).toString()
        return generatedView
    }

}