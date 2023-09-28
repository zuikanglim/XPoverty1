package com.example.xpoverty

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class News : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

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