package com.example.drunkapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import java.text.DecimalFormat
import java.text.NumberFormat
import kotlin.random.Random

class CodeSubstitutionTest : AppCompatActivity() {
    val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code_substitution_test)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        val timer = findViewById<View>(R.id.timerSub) as TextView

        object : CountDownTimer(20000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                // Used for formatting digit to be in 2 digits only
                val f: NumberFormat = DecimalFormat("00")
                val hour = millisUntilFinished / 3600000 % 24
                val min = millisUntilFinished / 60000 % 60
                val sec = millisUntilFinished / 1000 % 60
                timer.setText(
                    f.format(sec)

                )

            }

            // When the task is over it will print 00:00:00 there
            override fun onFinish() {
                timer.setText("00:00:00")
            }
        }.start()


        val text = findViewById(R.id.codetext) as TextView
        val codedesc = findViewById(R.id.codesubsidesc) as TextView
        val num0 = findViewById(R.id.num0) as Button
        val num1 = findViewById(R.id.num1) as Button
        val num2 = findViewById(R.id.num2) as Button
        val num3 = findViewById(R.id.num3) as Button
        val num4 = findViewById(R.id.num4) as Button
        val num5 = findViewById(R.id.num5) as Button
        val num6 = findViewById(R.id.num6) as Button
        val num7 = findViewById(R.id.num7) as Button
        val num8 = findViewById(R.id.num8) as Button
        val num9 = findViewById(R.id.num9) as Button
        val arr = charArrayOf(
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        )
        val code0 = findViewById(R.id.code0) as Button
        val code1 = findViewById(R.id.code1) as Button
        val code2 = findViewById(R.id.code2) as Button
        val code3 = findViewById(R.id.code3) as Button
        val code4 = findViewById(R.id.code4) as Button
        val code5 = findViewById(R.id.code5) as Button
        val code6 = findViewById(R.id.code6) as Button
        val code7 = findViewById(R.id.code7) as Button
        val code8 = findViewById(R.id.code8) as Button
        val code9 = findViewById(R.id.code9) as Button
        val randarr = intArrayOf(
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
            14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25
        )
        val numrand = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
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
        }, 5000)
        num0.setOnClickListener {
            if (code0.text == text.text) {
                if(count < 9) {count++}
                correct++
                text.setText(arr[randarr[numrand[count]]].toString())
            }
            if(correct == 10)
                text.setText("")
        }
        num1.setOnClickListener {
            if (code1.text == text.text) {
                if(count < 9) {count++}
                correct++
                text.setText(arr[randarr[numrand[count]]].toString())
            }
            if(correct == 10)
                text.setText("")
        }
        num2.setOnClickListener {
            if (code2.text == text.text) {
                if(count < 9) {count++}
                correct++
                text.setText(arr[randarr[numrand[count]]].toString())
            }
            if(correct == 10)
                text.setText("")
        }
        num3.setOnClickListener {
            if (code3.text == text.text) {
                if(count < 9) {count++}
                correct++
                text.setText(arr[randarr[numrand[count]]].toString())
            }
            if(correct == 10)
                text.setText("")
        }
        num4.setOnClickListener {
            if (code4.text == text.text) {
                if(count < 9) {count++}
                correct++
                text.setText(arr[randarr[numrand[count]]].toString())
            }
            if(correct == 10)
                text.setText("")
        }
        num5.setOnClickListener {
            if (code5.text == text.text) {
                if(count < 9) {count++}
                correct++
                text.setText(arr[randarr[numrand[count]]].toString())
            }
            if(correct == 10)
                text.setText("")
        }
        num6.setOnClickListener {
            if (code6.text == text.text) {
                if(count < 9) {count++}
                correct++
                text.setText(arr[randarr[numrand[count]]].toString())
            }
            if(correct == 10)
                text.setText("")
        }
        num7.setOnClickListener {
            if (code7.text == text.text) {
                if(count < 9) {count++}
                correct++
                text.setText(arr[randarr[numrand[count]]].toString())
            }
            if(correct == 10)
                text.setText("")
        }
        num8.setOnClickListener {
            if (code8.text == text.text) {
                if(count < 9){count++}
                correct++
                text.setText(arr[randarr[numrand[count]]].toString())
            }
            if(correct == 10)
                text.setText("")
        }
        num9.setOnClickListener {
            if (code9.text == text.text) {
                if(count < 9) {count++}
                correct++
                text.setText(arr[randarr[numrand[count]]].toString())
            }
            if(correct == 10)
                text.setText("")
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
            text.setText("Correct = $correct")
            val intent = Intent(this, Results::class.java)
            val b = getIntent().extras
            if (b != null) {
                intent.putExtras(b)
            }

            intent.putExtra("substitutioncorrect", correct)
            text.setTextSize(14F)
            startActivity(intent)
            finish()
        }, 20000)
    }
    override fun onBackPressed() {
        this.finish()
        super.onBackPressed()
    }
    override fun onPause() {
        handler.removeCallbacksAndMessages(null);
        super.onPause()
    }
}