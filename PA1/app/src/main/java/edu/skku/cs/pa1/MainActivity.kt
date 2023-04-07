package edu.skku.cs.pa1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val wordleText = findViewById<EditText>(R.id.wordleText)
        val submitBtn = findViewById<Button>(R.id.submitBtn)
        val items = ArrayList<wordList>()

        submitBtn.setOnClickListener {
            items.add(wordList(wordleText.toString().toList(), listOf(1,1,1,1,1)))
            val wordListView = findViewById<ListView>(R.id.wordListView)
            val myAdapter = wordListAdapter(applicationContext,items)
            wordListView.adapter = myAdapter
        }
    }
}