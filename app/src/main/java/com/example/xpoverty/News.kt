package com.example.xpoverty

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class News : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val btn1 = findViewById<Button>(R.id.view1)
        btn1.setOnClickListener {
            val intent = Intent(this, News1::class.java)
            startActivity(intent)
            val sU = AnimationUtils.loadAnimation(this,R.anim.scale_up)
            btn1.startAnimation(sU)
        }

        val btn2 = findViewById<Button>(R.id.view2)
        btn2.setOnClickListener {
            val intent = Intent(this, News2::class.java)
            startActivity(intent)
            val sU = AnimationUtils.loadAnimation(this,R.anim.scale_up)
            btn2.startAnimation(sU)
        }

        val btn3 = findViewById<Button>(R.id.view3)
        btn3.setOnClickListener {
            val intent = Intent(this, News3::class.java)
            startActivity(intent)
            val sU = AnimationUtils.loadAnimation(this,R.anim.scale_up)
            btn3.startAnimation(sU)
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