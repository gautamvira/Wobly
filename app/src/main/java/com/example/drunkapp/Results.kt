package com.example.drunkapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import android.widget.ProgressBar
import androidx.core.view.isVisible


class Results : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)
        val testres = findViewById(R.id.Resulttext) as TextView
        val pres = findViewById(R.id.progressBar) as ProgressBar
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        val b = getIntent().extras
        val reaction = b?.getLong("reaction").toString()
        val screentapaccuracy = b?.getInt("screentapaccuracy").toString()
        val screentapcorrect = b?.getInt("screentapcorrect").toString()
        val substitutioncorrect = b?.getInt("substitutioncorrect").toString()
        val visualincorrect = b?.getInt("VisualIncorrect").toString()




        if(b?.getString("TestType") == "Impair"){
            val data = applicationContext.getSharedPreferences("LastTest", 0)
            val editor = data.edit()
            editor.putString("userreaction", reaction)
            editor.putString("userscreentapaccuracy", screentapaccuracy)
            editor.putString("userscreentapcorrect", screentapcorrect)
            editor.putString("usersubstitutioncorrect", substitutioncorrect)
            editor.putString("uservisualcorrect", visualincorrect)

            val total_diff = pointdiff() * 2
            editor.putInt("pointdiff", total_diff)
            editor.apply()


            pres.isVisible = true
            pres.isIndeterminate = false
            pres.setProgress(total_diff)
            if(total_diff < 30){
                pres.progressTintList = ColorStateList.valueOf(Color.GREEN)
                //pres.progressDrawable.setTint(R.color.green)
                testres.setText("Visual Impairment: LOW")
            }
            else if(total_diff < 60){
                pres.progressTintList = ColorStateList.valueOf(Color.YELLOW)
                testres.setText("Visual Impairment: ABOVE AVERAGE")
            }
            else{
                pres.progressTintList = ColorStateList.valueOf(Color.RED)
                testres.setText("Visual Impairment: VERY HIGH")
            }





        }
        else if(b?.getString("TestType") == "setup"){
            val data = applicationContext.getSharedPreferences("UserBaseVal", 0)
            val editor = data.edit()
            editor.putString("userreaction", reaction)
            editor.putString("userscreentapaccuracy", screentapaccuracy)
            editor.putString("userscreentapcorrect", screentapcorrect)
            editor.putString("usersubstitutioncorrect", substitutioncorrect)
            editor.putString("uservisualcorrect", visualincorrect)
            editor.apply()

            testres.setText("Your Setup was complete")

        }





    }
    fun HomeIntent(view: View?) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun pointdiff():Int{
        val b = getIntent().extras
        val nreaction = b?.getLong("reaction")
        val nscreentapaccuracy = b?.getInt("screentapaccuracy")
        val nscreentapcorrect = b?.getInt("screentapcorrect")
        val nsubstitutioncorrect = b?.getInt("substitutioncorrect")
        val nvisualincorrect = b?.getInt("VisualIncorrect")

        val datax = applicationContext.getSharedPreferences("UserBaseVal", 0)
        val ogreaction = datax.getString("userreaction", "NULL")?.toFloat()
        val oguserscreentapaccuracy = datax.getString("userscreentapaccuracy", "NULL")?.toFloat()
        val oguserscreentapcorrect = datax.getString("userscreentapcorrect", "NULL")?.toFloat()
        val ogusersubstitutioncorrect = datax.getString("usersubstitutioncorrect", "NULL")?.toFloat()
        val oguservisualcorrect = datax.getString("uservisualcorrect", "NULL")?.toFloat()

        val ogreactiondiff = (nreaction?.let { ogreaction?.minus(it) }?.let { Math.abs(it.toInt()) })?.div(100)
        val oguserscreentapaccuracydiff = nscreentapaccuracy?.let { oguserscreentapaccuracy?.minus(it) }?.let { Math.abs(it.toInt()) }
        val oguserscreentapcorrectdiff = oguserscreentapcorrect?.let { nscreentapcorrect?.minus(it) }?.let { Math.abs(it.toInt()) }
        val ogusersubstitutioncorrectdiff = nsubstitutioncorrect?.let { ogusersubstitutioncorrect?.minus(it) }?.let { Math.abs(it.toInt()) }
        val oguservisualcorrectdiff = nvisualincorrect?.let { oguservisualcorrect?.minus(it) }?.let { Math.abs(it.toInt()) }

        return ogreactiondiff!! + oguserscreentapaccuracydiff!! + oguserscreentapcorrectdiff!! + ogusersubstitutioncorrectdiff!! + oguservisualcorrectdiff!!


    }
}