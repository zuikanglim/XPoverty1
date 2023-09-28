package com.example.xpoverty

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView

class AboutUs : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)

        val contactTextView = findViewById<TextView>(R.id.contactText)
        contactTextView.setOnClickListener {
            val dialIntentT = Intent(Intent.ACTION_DIAL)
            dialIntentT.data = Uri.parse("tel:"+ "0123456789")
            startActivity(dialIntentT)
        }

        val bnv =  findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bnv.selectedItemId = R.id.aboutUs

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
