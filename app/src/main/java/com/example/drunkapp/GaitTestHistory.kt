package com.example.drunkapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.ImageView
import android.widget.TextView
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.File
import kotlin.math.pow

class GaitTestHistory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gait_test_history)
        val gyroscope2 = findViewById(R.id.gyroscope5) as TextView
        val gyroscope3 = findViewById(R.id.gyroscope6) as TextView
        val accelerometer1 = findViewById(R.id.accelerometer4) as TextView
        val accelerometer2 = findViewById(R.id.accelerometer5) as TextView
        val imageview = findViewById(R.id.imageView) as ImageView
        val filename1 = "setup_motion1.csv"
        val filename2 = "setup_motion2.csv"
        val filename3 = "test_motion1.csv"
        val filename4 = "test_motion2.csv"
        val path = getExternalFilesDir(null)
        val fileOut1 = File(path, filename3)
        val fileOut2 = File(path, filename4)
        var accelx: Float = 0F
        var accely: Float = 0F
        var accelz: Float = 0F
        var gyrox: Float = 0F
        var gyroy: Float = 0F
        var gyroz: Float = 0F
        var rows: List<List<String>>
        var rows2: List<List<String>>
        if (File(path, filename1).exists() && File(path, filename2).exists()) {
            var count1 = 0
            var counter = 0
            var count2 = 0
            var counter2 = 0
            var summ1 = 0F
            var summ2 = 0F
            var summ3 = 0F
            var accelx2 = 0F
            var accely2 = 0F
            var accelz2 = 0F

            val rows = csvReader().readAll(fileOut1)
            csvReader().open("${path}/${filename1}") {
                readAllAsSequence().forEach { row: List<String> ->
                    if (count1 != 0  && count1 < rows.size) {
                        accelx += (Math.abs(rows[count1][1].toFloat() - row[1].toFloat())/Math.abs(row[1].toFloat()))
                        summ1 += Math.abs(row[1].toFloat())
                        accely += (Math.abs(rows[count1][2].toFloat() - row[2].toFloat())/Math.abs(row[2].toFloat()))
                        summ2 += Math.abs(row[2].toFloat())
                        accelz += (Math.abs(rows[count1][3].toFloat() - row[3].toFloat())/Math.abs(row[3].toFloat()))
                        summ3 +=  Math.abs(row[3].toFloat())
                        accelx2 += row[1].toFloat()
                        accely2 += row[2].toFloat()
                        accelz2 += row[3].toFloat()
                        counter++

                    }
                    count1++
                }
            }
            var summx = 0F
            var summy = 0F
            var summz = 0F
            var gyrox2 = 0F
            var gyroy2 = 0F
            var gyroz2 = 0F
            rows2 = csvReader().readAll(fileOut2)
            csvReader().open("${path}/${filename2}") {
                readAllAsSequence().forEach { row: List<String> ->
                    if (count2 != 0  && count2 < rows2.size) {
                        gyrox += (Math.abs(rows2[count2][1].toFloat() - row[1].toFloat())/Math.abs(row[1].toFloat()))
                        summx += Math.abs(row[1].toFloat())
                        gyroy += (Math.abs(rows2[count2][2].toFloat() - row[2].toFloat())/Math.abs(row[2].toFloat()))
                        summy+= Math.abs(row[2].toFloat())
                        gyroz += (Math.abs(rows2[count2][3].toFloat() - row[3].toFloat())/Math.abs(row[3].toFloat()))
                        summz += Math.abs(row[3].toFloat())
                        gyrox2 += row[1].toFloat()
                        gyroy2 += row[2].toFloat()
                        gyroz2 += row[3].toFloat()
                        counter2++
                    }
                    count2++
                }
            }

            val averagex = ((accelx/counter))
            println(averagex)
            val averagey = ((accely/counter))
            println(averagey)
            val averagez = ((accelz/counter))
            println(averagez)
            var avgaccel = averagex + averagey + averagez
            avgaccel /= 3
            val avgx = ((gyrox/counter2))
            println(avgx)
            val avgy = ((gyroy/counter2))
            println(avgy)
            val avgz = ((gyroz/counter2))
            println(avgz)
            var avggyro = avgx + avgy + avgz
            avggyro /= 3

            if(avggyro < 4) {
                gyroscope3.setText("Your movement was not registered correctly. Please Try Again.")
                imageview.setImageDrawable(resources.getDrawable(R.drawable.confused))
            }
            else{
                if(avgaccel <= 0.7){
                    avgaccel *= 10
                    avggyro = (Math.abs(avggyro - 6)/avggyro) * 100
                }
                else {
                    val sigacc = (1/(1+((Math.E).pow(-avgaccel.toDouble()))))
                    val siggyr = (1/(1+((Math.E).pow(-avggyro.toDouble()))))
                    val sigavg = ((sigacc + siggyr)/2) * 100
                    avgaccel = (Math.abs(avgaccel - 0.7F) / avgaccel) * 100
                    avggyro = (Math.abs(avggyro - 6) / avggyro) * 100
                }
                val peravg = (avgaccel + avggyro) / 2
                if(peravg <= 30){
                    gyroscope2.setText("Your walking pattern was almost the same.")
                    gyroscope3.setText("Difference Level: LOW")
                    gyroscope3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
                    gyroscope3.setTextColor(resources.getColor(R.color.green))
                    gyroscope3.setBackgroundColor(resources.getColor(R.color.black))
                    imageview.setImageDrawable(resources.getDrawable(R.drawable.standing))
                }
                else if(peravg > 30 && peravg < 50){
                    gyroscope2.setText("Your walking pattern was a little different.")
                    gyroscope3.setText("Difference Level: MEDIUM")
                    gyroscope3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
                    gyroscope3.setTextColor(resources.getColor(R.color.yellow))
                    gyroscope3.setBackgroundColor(resources.getColor(R.color.black))
                    imageview.setImageDrawable(resources.getDrawable(R.drawable.mediumwalk))
                }
                else{
                    gyroscope2.setText("Your walking pattern was significantly irregular.")
                    gyroscope3.setText("Difference Level: HIGH")
                    gyroscope3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
                    gyroscope3.setTextColor(resources.getColor(R.color.red))
                    gyroscope3.setBackgroundColor(resources.getColor(R.color.black))
                    imageview.setImageDrawable(resources.getDrawable(R.drawable.falling))

                }

            }
            accelerometer1.setText("")
            accelerometer2.setText("")
        }
        else{
            gyroscope3.setText("Setup not completed. Please finish the setup test in settings first.")
            accelerometer1.setText("")
            accelerometer2.setText("")
        }
    }


}