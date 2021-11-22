package com.example.drunkapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import android.content.SharedPreferences
import android.view.View
import android.widget.ProgressBar
import androidx.core.view.isVisible


class Results : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)
        val reactionresult = findViewById(R.id.reactionresult) as TextView
        val screentapresult = findViewById(R.id.screentapresult) as TextView
        val codesubresult = findViewById(R.id.codesubresult) as TextView
        val visualtest = findViewById(R.id.visualresult) as TextView
        val testres = findViewById(R.id.Resulttext) as TextView
        val pres = findViewById(R.id.progressBar) as ProgressBar
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        val b = getIntent().extras
        val reaction = b?.getLong("reaction").toString()
        val screentapaccuracy = b?.getInt("screentapaccuracy").toString()
        val screentapcorrect = b?.getInt("screentapcorrect").toString()
        val substitutioncorrect = b?.getInt("substitutioncorrect").toString()
        val visualincorrect = b?.getInt("VisualIncorrect").toString()

        reactionresult.setText("$reaction ms")
        screentapresult.setText("Correct: $screentapcorrect Accuracy: $screentapaccuracy %")
        codesubresult.setText("Correct: $substitutioncorrect")
        visualtest.setText("Incorrect: $visualincorrect")



        if(b?.getString("TestType") == "Impair"){
            val data = applicationContext.getSharedPreferences("LastTest", 0)
            val editor = data.edit()
            editor.putString("userreaction", reaction)
            editor.putString("userscreentapaccuracy", screentapaccuracy)
            editor.putString("userscreentapcorrect", screentapcorrect)
            editor.putString("usersubstitutioncorrect", substitutioncorrect)
            editor.putString("uservisualcorrect", visualincorrect)
            editor.apply()

           var total_diff = pointdiff() * 2
            testres.setText("Total Diffrence in your points was  $total_diff")
            pres.isVisible = true
            pres.isIndeterminate = false
            pres.setProgress(total_diff)
            if(total_diff < 30){
                pres.setBackgroundColor(resources.getColor(R.color.green))
            }
            else if(total_diff < 60){

                pres.setBackgroundColor(resources.getColor(R.color.yellow))
            }
            else{
                pres.setBackgroundColor(resources.getColor(R.color.red))
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