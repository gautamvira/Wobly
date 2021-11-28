package com.example.drunkapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

    }
    override fun onStart() {
        super.onStart()
        setupBottomNavigationMenu()
    }
    private fun setupBottomNavigationMenu() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.fragments)

        bottomNavigationView.setupWithNavController(navController)
    }


    fun ReactionTestIntent(view: View?) {
        val intent = Intent(this, ReactionTest::class.java)
        intent.putExtra("TestType", "Impair")
        startActivity(intent)
        //finish()
    }

    fun SetupReactionTestIntent(view: View?) {
        val intent = Intent(this, ReactionTest::class.java)
        intent.putExtra("TestType", "setup")
        startActivity(intent)
       //finish()
    }

    fun GaitTestIntent(view: View?){
        val intent = Intent(this, GaitTest::class.java)
        intent.putExtra("TestType", "Impair")
        startActivity(intent)
        //()
    }
    fun SetupGaitTestIntent(view: View?){
        val intent = Intent(this, GaitTest::class.java)
        intent.putExtra("TestType", "setup")
        startActivity(intent)
        //()
    }

    fun UserProfileIntent(view: View?) {
        val intent = Intent(this, UserSetUp::class.java)
        startActivity(intent)
        //finish()
    }

    fun UserHistoryIntent(view: View?) {
        val intent = Intent(this, UserHistory::class.java)
        startActivity(intent)
        //finish()
    }

    fun GaitTestHistory(view: View?){
        val intent = Intent(this, GaitTestHistory::class.java)
        startActivity(intent)
    }

    override fun onDestroy() {
        var fileOut1: File
        var fileOut2: File
        val filename1 = "test_motion1.csv"
        val filename2 = "test_motion2.csv"
        //val sensir = GaitTest()
        //sensir.sensorManager.unregisterListener(GaitTest())
        var path = getExternalFilesDir(null)
        fileOut1 = File(path, filename1)
        fileOut2 = File(path, filename2)
        //fileOut1.delete()
        //fileOut2.delete()
        super.onDestroy()
    }

}