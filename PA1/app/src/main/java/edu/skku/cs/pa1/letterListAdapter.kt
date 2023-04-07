package edu.skku.cs.pa1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat

class letterListAdapter(val context: Context, val data: ArrayList<letterList>):BaseAdapter() {
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
        val generatedView = inflater.inflate(R.layout.letter,null)
        val letter = generatedView.findViewById<TextView>(R.id.letterView)
        colorCheck(letter,data[p0].type)
        letter.text = data[p0].letter.toString().toUpperCase()
        return generatedView
    }
    fun colorCheck(p0:TextView,p1:Int){  //객체의 배경색과 글자색을 바꿔주는 함수
        when(p1){
            0 -> {  p0.setBackgroundColor(ContextCompat.getColor(context,R.color.background_out))
                p0.setTextColor(ContextCompat.getColor(context,R.color.text_out)) }
            1 -> {  p0.setBackgroundColor(ContextCompat.getColor(context,R.color.background_ball))
                p0.setTextColor(ContextCompat.getColor(context,R.color.text_ball)) }
            2 -> {  p0.setBackgroundColor(ContextCompat.getColor(context,R.color.background_strike))
                p0.setTextColor(ContextCompat.getColor(context,R.color.text_strike )) }
        }
    }
}