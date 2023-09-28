package com.example.xpoverty

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.xpoverty.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    lateinit var userProfile: UserProfile

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().getReference("UserDB")

        binding.btnContactUsL.setOnClickListener(){
                val dialIntentT = Intent(Intent.ACTION_DIAL)
                dialIntentT.data = Uri.parse("tel:"+ "0123456789")
                startActivity(dialIntentT)
        }

        binding.btnLogin.setOnClickListener(){
            if (TextUtils.isEmpty(binding.etEmailL.text.toString()) || TextUtils.isEmpty(binding.etPassword.text.toString())) {
                binding.tvResult.text = "Please Don't Empty!!"
            }else{
                userLogin(binding.etEmailL.text.toString(), binding.etPassword.text.toString())
            }
        }

        binding.btnRegister.setOnClickListener(){
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        binding.tvForgotPassword.setOnClickListener() {
            if(TextUtils.isEmpty(binding.etEmailL.text.toString())){
                Toast.makeText(this,"Please enter your email", Toast.LENGTH_SHORT).show()
            }else{
                auth.sendPasswordResetEmail(binding.etEmailL.text.toString()).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this,"Successfully send, Please check your email account", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        //supportActionBar?.hide()
    }

    private fun userLogin(email: String, psw: String) {
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        auth.signInWithEmailAndPassword(email, psw)
            .addOnSuccessListener {
                readData(binding.etUsername.text.toString())
            }
            .addOnFailureListener{ ex->
                binding.tvResult.text = ex.message
            }
    }

    private fun readData(username: String){
        database.child("userList").child(username).get().addOnSuccessListener {
            if(it.exists()){
                userProfile = UserProfile(it.child("username").value.toString(),Integer.parseInt(it.child("phoneNumber").value.toString()),it.child("email").value.toString())

                val intent = Intent(this, Account::class.java)
                intent.putExtra("username", userProfile.username);
                intent.putExtra("email", userProfile.email);
                intent.putExtra("phoneNumber", userProfile.phoneNumber);
                Toast.makeText(this,"Successfully Read", Toast.LENGTH_SHORT).show()
                binding.tvResult.text = "Login Successfully!!!"
                startActivity(intent)

            }else{
                Toast.makeText(this,"User Doesn't Exist", Toast.LENGTH_SHORT).show()
                binding.tvResult.text = "Username doesn't exist!!!"
            }
        }.addOnFailureListener{
            Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
            binding.tvResult.text = "Login Failed!!!"
        }
    }
}