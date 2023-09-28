package com.example.xpoverty

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.content.Intent
import android.widget.ImageView
import com.example.xpoverty.databinding.AccountBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Account : AppCompatActivity() {
    private lateinit var binding : AccountBinding
    val REQUEST_CODE = 100
    lateinit var imgProfile: ImageView
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //declaration
        imgProfile = findViewById(R.id.imgProfile)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference

        val user = auth.currentUser

        binding.tvDetails.text = "loongxi"
        binding.tvEmail.text = "loongxi@gmail.com"
        binding.tvPhoneNo.text = "01110448596"

        if(user != null && intent.getStringExtra("username") != null){
            val intent = intent
            val username = intent.getStringExtra("username")
            val email = intent.getStringExtra("email")
            val phoneNumber = intent.getIntExtra("phoneNumber", 0)
            binding.tvDetails.text = username
            binding.tvEmail.text = email
            binding.tvPhoneNo.text = phoneNumber.toString()
        }

        binding.tvLogout.setOnClickListener(){
            auth.signOut()
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        //buttons function
        binding.tvForm.setOnClickListener() {
            val intent = Intent(this, DonationForm::class.java)
            startActivity(intent)
        }

        binding.tvHistory.setOnClickListener() {
            val intent = Intent(this, History::class.java)
            startActivity(intent)
        }

        binding.btnChgPic.setOnClickListener() {
            openGalleryForImage()
        }


/*        var getdata = object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                var sb = StringBuilder()
                for(i in p0.children){
                    var username = i.child("username").getValue()
                    var email = i.child("email").getValue()
                    var phoneNumber = i.child("phoneNumber").getValue()
                    sb.append("${i.key} $username $email $phoneNumber")
                }
                binding.tvPhoneNo.setText(sb)
            }
        }
        database.addValueEventListener(getdata)
        database.addListenerForSingleValueEvent(getdata)*/

        val bnv =  findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bnv.selectedItemId = R.id.account

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

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){
            imgProfile.setImageURI(data?.data) // handle chosen image
        }
    }
}