package com.example.xpoverty

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

import android.net.Uri
import android.view.MenuItem
import com.example.xpoverty.databinding.ActivityEvent1Binding


class Event1 : AppCompatActivity() {
    private lateinit var binding: ActivityEvent1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEvent1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnM = findViewById<Button>(R.id.mapL)
        btnM.setOnClickListener {
            val uri = "https://www.google.com/maps/place/TOVA+QDRO+Webinar+Registration/@40.7803956,-73.5032291,16z/data=!4m5!3m4!1s0x89c2819851815555:0xf73d57a269c6546a!8m2!3d40.7866905!4d-73.5028813"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            startActivity(intent)
        }
        // calling the action bar
        var actionBar = getSupportActionBar()

        // showing the back button in action bar
        if (actionBar != null) {
            actionBar.setTitle("Event")
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
        binding.btnRegisterEvent1.setOnClickListener(){
            val intent = Intent(this, RegisterEvent1::class.java)
            startActivity(intent)
        }
    }
    // this event will enable the back function to the button on press
    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }



}