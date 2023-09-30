package com.example.xpoverty

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

class Home : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val eventsFrame = findViewById<ConstraintLayout>(R.id.clevents)
        eventsFrame.setOnClickListener(){
            val intent = Intent(this, Event::class.java)
            startActivity(intent)
        }

        val introFrame = findViewById<ConstraintLayout>(R.id.clintro)
        introFrame.setOnClickListener(){
            val intent = Intent(this, Introduction::class.java)
            startActivity(intent)
        }

        val newsFrame = findViewById<ConstraintLayout>(R.id.clnews)
        newsFrame.setOnClickListener(){
            val intent = Intent(this, News::class.java)
            startActivity(intent)
        }

        val bnv =  findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bnv.selectedItemId = R.id.home

        bnv.setOnItemSelectedListener{
            when (it.itemId){
                R.id.home ->{
                    val intent = Intent(this, Home::class.java)
                    startActivity(intent)
                    return@setOnItemSelectedListener true
                }
                R.id.event ->{
                    val intent = Intent(this, Event::class.java)
                    startActivity(intent)
                    return@setOnItemSelectedListener true
                }
                R.id.aboutUs ->{
                    val intent = Intent(this, AboutUs::class.java)
                    startActivity(intent)
                    return@setOnItemSelectedListener true
                }
                R.id.account ->{
                    val intent = Intent(this, Account::class.java)
                    startActivity(intent)
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }
}