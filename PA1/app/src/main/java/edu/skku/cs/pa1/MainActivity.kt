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
        val grayItems = ArrayList<letterList>()
        val yellowItems = ArrayList<letterList>()
        val greenItems = ArrayList<letterList>()

        //4. file 받아와서 answer word 랜덤하게 뽑기
        val fileInputStream = applicationContext.assets.open("wordle_words.txt")
        val fileInputString = fileInputStream.readBytes().toString(Charsets.UTF_8)
        val fileInputList = fileInputString.split("\n").map{it.trim()} //4-1. 전체 dictionary word list
        var random = Random()
        var randomNum = random.nextInt(fileInputList.count())
        //val answer = fileInputList.get(randomNum) //4-2. answer text 임의로 선정하기
        val answer = "lousy"//TEST
        println("total length: ${fileInputList.count()}, randomNum: ${randomNum}, random word: ${answer}") //TEST
        //5-1 adaptor for letter List
        val wordListView = findViewById<ListView>(R.id.wordListView)
        val grayLetterView = findViewById<ListView>(R.id.grayLetterView)
        val yellowLetterView = findViewById<ListView>(R.id.yellowLetterView)
        val greenLetterView = findViewById<ListView>(R.id.greenLetterView)
        val grayAdapter = letterListAdapter(applicationContext,grayItems)
        val yellowAdapter = letterListAdapter(applicationContext,yellowItems)
        val greenAdapter = letterListAdapter(applicationContext,greenItems)
        grayLetterView.adapter = grayAdapter
        yellowLetterView.adapter = yellowAdapter
        greenLetterView.adapter = greenAdapter
        //5. submit button
        submitBtn.setOnClickListener {
            val inputWord = wordleText.text.toString().toLowerCase().trim() // 5-1. input word를 소문자화 하고 trim 완료
            if(fileInputList.contains(inputWord)) //5-2. input word가 사전에 있는 경우
            {
                val integerList = wordle(inputWord,answer) // 정답 int list
                items.add(wordList(inputWord.toUpperCase().toList(),integerList))
                // 5-2. word list 구현 완료
                val myAdapter = wordListAdapter(applicationContext,items)
                wordListView.adapter = myAdapter
                // 5-3. word letter list 구현 완료
                for(i in 0 until 5){
                    val temp = letterList(inputWord.get(i).toString(),integerList[i])
                    var flag = 1
                    when(integerList[i]){
                        0->{
                            for(j in 0 until grayItems.count()){
                                if(grayItems.get(j).letter == temp.letter){
                                    flag = 0
                                }
                            }
                            if(flag == 1){
                                grayItems.add(temp)
                                grayAdapter.notifyDataSetChanged()
                            }
                        }
                        1-> {
                            for (j in 0 until yellowItems.count()) {
                                if (yellowItems.get(j).letter == temp.letter) {
                                    flag = 0
                                }
                            }
                            for (j in 0 until greenItems.count()) {
                                if (greenItems.get(j).letter == temp.letter) {
                                    flag = 0
                                }
                            }
                            if (flag == 1) {
                                yellowItems.add(temp)
                                yellowAdapter.notifyDataSetChanged()
                            }
                        }
                        2->{
                            for (j in 0 until greenItems.count()) {
                                if (greenItems.get(j).letter == temp.letter) {
                                    flag = 0
                                }
                            }
                            for (j in 0 until yellowItems.count()) {
                                if (yellowItems.get(j).letter == temp.letter) {
                                    yellowItems.remove(yellowItems.get(j))
                                    yellowAdapter.notifyDataSetChanged()
                                    break
                                }
                            }
                            if(flag == 1){
                                greenItems.add(temp)
                                greenAdapter.notifyDataSetChanged()
                            }
                        }
                    }
                }
                // 5-4. wordle Text 부분 비우기
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