package com.example.drunkapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.Image
import android.net.Uri
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import java.io.File
import java.io.FileWriter
import java.io.IOException
import android.util.TypedValue
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.nio.file.Files
import java.nio.file.Paths
import java.text.DecimalFormat
import java.text.NumberFormat
import kotlin.math.pow
import kotlin.properties.Delegates


class GaitTest : AppCompatActivity(), SensorEventListener {
    private lateinit var gyroscope1 : TextView
    private lateinit var gyroscope2 : TextView
    private lateinit var gyroscope3 : TextView
    private lateinit var accelerometer1 : TextView
    private lateinit var accelerometer2 : TextView
    private lateinit var imageview : ImageView
    private lateinit var sensorManager : SensorManager
    private var stoptime = 0L
    private var starttime = 0L
    lateinit var fileOut1: File
    lateinit var fileOut2: File
    var handler = Handler()
    var h = Handler()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gait_test)
        val b = getIntent().extras
        val testtype = b?.getString("TestType").toString()
        //gyroscope1 = findViewById(R.id.gyroscope1) as TextView
        gyroscope2 = findViewById(R.id.gyroscope2) as TextView
        gyroscope3 = findViewById(R.id.gyroscope3) as TextView
        accelerometer1 = findViewById(R.id.accelerometer1) as TextView
        accelerometer2 = findViewById(R.id.accelerometer2) as TextView
        imageview = findViewById(R.id.imageView) as ImageView

        object : CountDownTimer(5000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                // Used for formatting digit to be in 2 digits only
                val f: NumberFormat = DecimalFormat("00")
                val hour = millisUntilFinished / 3600000 % 24
                val min = millisUntilFinished / 60000 % 60
                val sec = (millisUntilFinished / 1000 % 60) + 1
                accelerometer1.setText(
                    f.format(sec)

                )

            }

            override fun onFinish() {
                accelerometer1.setText("Start Walking")
            }
        }.start()
        h.postDelayed({
            gyroscope2.setText("")
            sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER_UNCALIBRATED).also {
                sensorManager.registerListener(this, it, 10000)
            }
            sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE).also {
                sensorManager.registerListener(this, it, 10000)
            }
            starttime = System.nanoTime()
        }, 5000)

        val headers1 = "Time,Accel X,Accel Y,Accel Z"
        val filename1 = "setup_motion1.csv"
        val headers2 = "Time,Gyro X,Gyro Y,Gyro Z"
        val filename2 = "setup_motion2.csv"
        val filename3 = "test_motion1.csv"
        val filename4 = "test_motion2.csv"
        val path = getExternalFilesDir(null)

        //create fileOut object
        if(testtype == "setup") {
            fileOut1 = File(path, filename1)
            fileOut2 = File(path, filename2)
        }
        else if(testtype == "Impair"){
            fileOut1 = File(path, filename3)
            fileOut2 = File(path, filename4)
        }
        fileOut1.delete()
        fileOut2.delete()
        fileOut1.createNewFile()
        fileOut1.appendText(headers1)
        fileOut1.appendText("\n")
        fileOut2.createNewFile()
        fileOut2.appendText(headers2)
        fileOut2.appendText("\n")
        var accelx: Float = 0F
        var accely: Float = 0F
        var accelz: Float = 0F
        var gyrox: Float = 0F
        var gyroy: Float = 0F
        var gyroz: Float = 0F
        var rows: List<List<String>>
        var rows2: List<List<String>>
        handler.postDelayed ({
            sensorManager.unregisterListener(this)
            if(testtype == "setup"){
                gyroscope2.setText("")
                gyroscope3.setText("Your setup is complete. You may go back.")
                accelerometer1.setText("")
                accelerometer2.setText("")
            }
            else if(testtype == "Impair") {
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

                    rows = csvReader().readAll(fileOut1)
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
        }, 10000)
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        if(p0?.sensor?.type == Sensor.TYPE_ACCELEROMETER_UNCALIBRATED){
            val xax = p0.values[0]
            val yax = p0.values[1]
            val zax = p0.values[2]
            stoptime = System.nanoTime()
            val currtime = (stoptime - starttime)
            fileOut1.appendText((currtime/1000000).toString())
            fileOut1.appendText(",")
            fileOut1.appendText(xax.toString())
            fileOut1.appendText(",")
            fileOut1.appendText(yax.toString())
            fileOut1.appendText(",")
            fileOut1.appendText(zax.toString())
            fileOut1.appendText("\n")
            /*
                accelerometer1.setText("Accelerometer X: ${xax.toInt()}")
                accelerometer2.setText("Accelerometer Y: ${yax.toInt()}")
                accelerometer3.setText("Accelerometer Z: ${zax.toInt()}")

             */

        }
        else if(p0?.sensor?.type == Sensor.TYPE_GYROSCOPE) {
            val xax = p0.values[0]
            val yax = p0.values[1]
            val zax = p0.values[2]
            stoptime = System.nanoTime()
            val currtime = (stoptime - starttime)
            fileOut2.appendText((currtime/1000000).toString())
            fileOut2.appendText(",")
            fileOut2.appendText(xax.toString())
            fileOut2.appendText(",")
            fileOut2.appendText(yax.toString())
            fileOut2.appendText(",")
            fileOut2.appendText(zax.toString())
            fileOut2.appendText("\n")
            /*
                gyroscope1.setText("Gyroscope X: ${xax.toInt()}")
                gyroscope2.setText("Gyroscope Y: ${yax.toInt()}")
                gyroscope3.setText("Gyroscope Z: ${zax.toInt()}")

             */

        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        return
    }

    override fun onPause() {
        handler.removeCallbacksAndMessages(null);
        h.removeCallbacksAndMessages(null)
        super.onPause()
    }

    override fun onBackPressed() {
        this.finish()
        super.onBackPressed()
    }

    override fun onDestroy() {
        //sensorManager.unregisterListener(this)
        super.onDestroy()
    }
}