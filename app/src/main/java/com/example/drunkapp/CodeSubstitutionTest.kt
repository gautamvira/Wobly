package com.example.drunkapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class CodeSubstitutionTest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code_substitution_test)
        var text = findViewById(R.id.codetext) as TextView
        var codedesc = findViewById(R.id.codesubsidesc) as TextView
        var num0 = findViewById(R.id.num0) as Button
        var num1 = findViewById(R.id.num1) as Button
        var num2 = findViewById(R.id.num2) as Button
        var num3 = findViewById(R.id.num3) as Button
        var num4 = findViewById(R.id.num4) as Button
        var num5 = findViewById(R.id.num5) as Button
        var num6 = findViewById(R.id.num6) as Button
        var num7 = findViewById(R.id.num7) as Button
        var num8 = findViewById(R.id.num8) as Button
        var num9 = findViewById(R.id.num9) as Button
        val arr =  charArrayOf('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z')
        var code0 = findViewById(R.id.code0) as Button
        var code1 = findViewById(R.id.code1) as Button
        var code2 = findViewById(R.id.code2) as Button
        var code3 = findViewById(R.id.code3) as Button
        var code4 = findViewById(R.id.code4) as Button
        var code5 = findViewById(R.id.code5) as Button
        var code6 = findViewById(R.id.code6) as Button
        var code7 = findViewById(R.id.code7) as Button
        var code8 = findViewById(R.id.code8) as Button
        var code9 = findViewById(R.id.code9) as Button
        val handler = Handler()
        var randarr = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
        14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25)
        var numrand = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        numrand.shuffle()
        randarr.shuffle()
        var count = 0
        var correct = 0
       handler.postDelayed({
            codedesc.setText("Code Substitution")
            code0.setText(arr[randarr[0]].toString())
            code0.setTextColor(getResources().getColor(R.color.white))
            code1.setText(arr[randarr[1]].toString())
            code1.setTextColor(getResources().getColor(R.color.white))
            code2.setText(arr[randarr[2]].toString())
            code2.setTextColor(getResources().getColor(R.color.white))
            code3.setText(arr[randarr[3]].toString())
            code3.setTextColor(getResources().getColor(R.color.white))
            code4.setText(arr[randarr[4]].toString())
            code4.setTextColor(getResources().getColor(R.color.white))
            code5.setText(arr[randarr[5]].toString())
            code5.setTextColor(getResources().getColor(R.color.white))
            code6.setText(arr[randarr[6]].toString())
            code6.setTextColor(getResources().getColor(R.color.white))
            code7.setText(arr[randarr[7]].toString())
            code7.setTextColor(getResources().getColor(R.color.white))
            code8.setText(arr[randarr[8]].toString())
            code8.setTextColor(getResources().getColor(R.color.white))
            code9.setText(arr[randarr[9]].toString())
            code9.setTextColor(getResources().getColor(R.color.white))
            text.setText(arr[randarr[numrand[count]]].toString())
           count++
        }, 5000)
        num0.setOnClickListener {
            if(code0.text == text.text){
                count++
                correct++
                text.setText(arr[randarr[numrand[count]]].toString())
            }
        }
        num1.setOnClickListener {
            if(code1.text == text.text){
                count++
                correct++
                text.setText(arr[randarr[numrand[count]]].toString())
            }
        }
        num2.setOnClickListener {
            if(code2.text == text.text){
                count++
                correct++
                text.setText(arr[randarr[numrand[count]]].toString())
            }
        }
        num3.setOnClickListener {
            if(code3.text == text.text){
                count++
                correct++
                text.setText(arr[randarr[numrand[count]]].toString())
            }
        }
        num4.setOnClickListener {
            if(code4.text == text.text){
                count++
                correct++
                text.setText(arr[randarr[numrand[count]]].toString())
            }
        }
        num5.setOnClickListener {
            if(code5.text == text.text){
                count++
                correct++
                text.setText(arr[randarr[numrand[count]]].toString())
            }
        }
        num6.setOnClickListener {
            if(code6.text == text.text){
                count++
                correct++
                text.setText(arr[randarr[numrand[count]]].toString())
            }
        }
        num7.setOnClickListener {
            if(code7.text == text.text){
                count++
                correct++
                text.setText(arr[randarr[numrand[count]]].toString())
            }
        }
        num8.setOnClickListener {
            if(code8.text == text.text){
                count++
                correct++
                text.setText(arr[randarr[numrand[count]]].toString())
            }
        }
        num9.setOnClickListener {
            if(code9.text == text.text){
                count++
                correct++
                text.setText(arr[randarr[numrand[count]]].toString())
            }
        }
        handler.postDelayed({
            num0.setOnClickListener(null)
            num1.setOnClickListener(null)
            num2.setOnClickListener(null)
            num3.setOnClickListener(null)
            num4.setOnClickListener(null)
            num5.setOnClickListener(null)
            num6.setOnClickListener(null)
            num7.setOnClickListener(null)
            num8.setOnClickListener(null)
            num9.setOnClickListener(null)
            val accuracy = (correct*100/10)
            text.setText("Correct = $accuracy%")
            text.setTextSize(14F)
        }, 20000)
        handler.postDelayed({
            val intent = Intent(this, VisualMemory::class.java)
            startActivity(intent)
        }, 22000)
    }
}