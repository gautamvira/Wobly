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
import androidx.core.content.ContextCompat
import java.text.DecimalFormat
import java.text.NumberFormat
import kotlin.random.Random

class ScreenTapTest : AppCompatActivity() {
    val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen_tap_test)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        val timer = findViewById<View>(R.id.TimerScreentap) as TextView

        object : CountDownTimer(16000, 1000) {
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

        var redcircle = findViewById(R.id.redbutton) as Button
        var bluecircle = findViewById(R.id.bluebutton) as Button
        var greencircle = findViewById(R.id.greenbutton) as Button
        var yellowcircle = findViewById(R.id.yellowbutton) as Button
        var textview = findViewById(R.id.screentaptext) as TextView
        var correct = 0
        var accuracy = 0
        var taps = 0
        var handler = Handler()
        handler.postDelayed({
                var color = Random.nextInt(0, 4)
                when (color) {
                    0 -> {
                        textview.setText("Tap Red")
                    }
                    1 -> {
                        textview.setText("Tap Purple")
                    }
                    2 -> {
                        textview.setText("Tap Green")
                    }
                    3 -> {
                        textview.setText("Tap Yellow")
                    }
                }
                redcircle.setOnClickListener {
                    if (color == 0) {
                        correct++
                        taps++
                        while(color == 0)
                            color = Random.nextInt(0, 4)
                        when (color) {
                            0 -> {
                                textview.setText("Tap Red")
                            }
                            1 -> {
                                textview.setText("Tap Purple")
                            }
                            2 -> {
                                textview.setText("Tap Green")
                            }
                            3 -> {
                                textview.setText("Tap Yellow")
                            }
                        }
                    }
                    else
                        taps++
                }
                bluecircle.setOnClickListener {
                    if (color == 1) {
                        correct++
                        taps++
                        while(color == 1)
                            color = Random.nextInt(0, 4)
                        when (color) {
                            0 -> {
                                textview.setText("Tap Red")
                            }
                            1 -> {
                                textview.setText("Tap Purple")
                            }
                            2 -> {
                                textview.setText("Tap Green")
                            }
                            3 -> {
                                textview.setText("Tap Yellow")
                            }
                        }
                    }
                    else
                        taps++
                }
                greencircle.setOnClickListener {
                    if (color == 2) {
                        correct++
                        taps++
                        while(color == 2)
                            color = Random.nextInt(0, 4)
                        when (color) {
                            0 -> {
                                textview.setText("Tap Red")
                            }
                            1 -> {
                                textview.setText("Tap Purple")
                            }
                            2 -> {
                                textview.setText("Tap Green")
                            }
                            3 -> {
                                textview.setText("Tap Yellow")
                            }
                        }
                    }
                    else
                        taps++
                }
                yellowcircle.setOnClickListener {
                    if (color == 3) {
                        correct++
                        taps++
                        while(color == 3)
                            color = Random.nextInt(0, 4)
                        when (color) {
                            0 -> {
                                textview.setText("Tap Red")
                            }
                            1 -> {
                                textview.setText("Tap Purple")
                            }
                            2 -> {
                                textview.setText("Tap Green")
                        }
                            3 -> {
                                textview.setText("Tap Yellow")
                            }
                        }
                    }
                    else
                        taps++
            }
        }, 5000)
        handler.postDelayed({
            if(taps != 0)
                accuracy = (correct * 100 / taps)
            else
                accuracy = 0
            textview.setText("Correct Taps: $correct   Accuracry: $accuracy%")
            textview.setTextColor(resources.getColor(R.color.black))
            yellowcircle.setOnClickListener(null)
            redcircle.setOnClickListener(null)
            greencircle.setOnClickListener(null)
            bluecircle.setOnClickListener(null)
                            }, 15000)
        handler.postDelayed({
            val intent = Intent(this, VisualMemory::class.java)
            val b = getIntent().extras
            if (b != null) {
                intent.putExtras(b)
            }
            intent.putExtra("screentapcorrect", correct)
            intent.putExtra("screentapaccuracy", accuracy)
            startActivity(intent)
            finish()
        }, 16000)
    }
    override fun onPause() {
        handler.removeCallbacksAndMessages(null);
        super.onPause()
    }
}