package com.example.drunkapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatDelegate

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
    }

    fun SetupReactionTestIntent(view: View?) {
        val intent = Intent(this, ReactionTest::class.java)
        intent.putExtra("TestType", "setup")
        startActivity(intent)
    }

    fun GaitTestIntent(view: View?){
        val intent = Intent(this, GaitTest::class.java)
        startActivity(intent)
    }

    fun UserTestIntent(view: View?) {
        val intent = Intent(this, UserSetUp::class.java)
        startActivity(intent)
    }

    fun UserHistoryIntent(view: View?) {
        val intent = Intent(this, UserHistory::class.java)
        startActivity(intent)
    }

}