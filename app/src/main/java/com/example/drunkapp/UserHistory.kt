package com.example.drunkapp

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import java.sql.Types.NULL

class UserHistory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_history)

        var His = findViewById(R.id.Historytext) as TextView
        val pres = findViewById(R.id.pres2) as ProgressBar
        var testres = findViewById(R.id.texty) as TextView


        val data = applicationContext.getSharedPreferences("LastTest", 0)

        val total_diff = data.getInt("pointdiff", NULL)

        His.setText("Last Test Result")
        pres.isVisible = true
        pres.isIndeterminate = false
        if (total_diff != null) {
            pres.setProgress(total_diff)
            if(total_diff < 30){
                pres.progressTintList = ColorStateList.valueOf(Color.GREEN)
                testres.setText("Visual Impairment: LOW")
            }
            else if(total_diff < 60){
                pres.progressTintList = ColorStateList.valueOf(Color.YELLOW)
                testres.setText("Visual Impairment: MEDIUM")
            }
            else{
                pres.progressTintList = ColorStateList.valueOf(Color.RED)
                testres.setText("Visual Impairment: HIGH")
            }
        }





    }
}