package edu.skku.cs.pa1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.*
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val wordleText = findViewById<EditText>(R.id.wordleText) //1. wordle edit text part
        val submitBtn = findViewById<Button>(R.id.submitBtn) //2. wordle text submit button part
        val items = ArrayList<wordList>() //3. 사용자가 예측한 단어를 저장해 놓는 배열

        //4. file 받아와서 answer word 랜덤하게 뽑기
        val fileInputStream = applicationContext.assets.open("wordle_words.txt")
        val fileInputString = fileInputStream.readBytes().toString(Charsets.UTF_8)
        val fileInputList = fileInputString.split("\n").map{it.trim()} //4-1. 전체 dictionary word list
        var random = Random()
        var randomNum = random.nextInt(fileInputList.count())
        val answer = fileInputList.get(randomNum) //4-2. answer text 임의로 선정하기
        println("total length: ${fileInputList.count()}, randomNum: ${randomNum}, random word: ${answer}") //TEST

        //5. submit button
        submitBtn.setOnClickListener {
            val inputWord = wordleText.text.toString().toLowerCase().trim() // 5-1. input word를 소문자화 하고 trim 완료
            if(fileInputList.contains(inputWord)) //5-2. input word가 사전에 있는 경우
            {
                val integerList = wordle(inputWord,answer) // 정답 int list
                items.add(wordList(inputWord.toUpperCase().toList(),integerList))
                // 5-2. word list 구현 완료
                val wordListView = findViewById<ListView>(R.id.wordListView)
                val myAdapter = wordListAdapter(applicationContext,items)
                wordListView.adapter = myAdapter
                wordleText.text = Editable.Factory.getInstance().newEditable("")
            }
            else{ //5-2. input word가 사전에 없는 경우
                Toast.makeText(applicationContext,"Word '${inputWord}' not in dictionary!", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun wordle(word1: String, word2: String): List<Int> { //wordle 구분 함수
        val result = mutableListOf<Int>()
        for (i in 0 until 5) {
            if (word1[i] == word2[i]) {
                result.add(2)
            } else if (word2.contains(word1[i])) {
                result.add(1)
            } else {
                result.add(0)
            }
        }
        return result
    }


}