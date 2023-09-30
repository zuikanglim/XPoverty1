package com.example.xpoverty

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.example.xpoverty.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.*

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        database = FirebaseDatabase.getInstance().getReference("UserDB")
        //val user = auth.currentUser

        binding.btnContactUsR.setOnClickListener(){
            val dialIntentT = Intent(Intent.ACTION_DIAL)
            dialIntentT.data = Uri.parse("tel:"+ "0123456789")
            startActivity(dialIntentT)
        }

        binding.btnRegisterR.setOnClickListener(){
            if (TextUtils.isEmpty(binding.etUsernameR.text.toString())){
                binding.tvResultR.text = "Username is empty!!"
            }else if(TextUtils.isEmpty(binding.etPasswordR.text.toString())){
                binding.tvResultR.text = "Password is empty!!"
            }else if(binding.etPasswordR.text.toString() != binding.etConfirmPassword.text.toString()){
                binding.tvResultR.text = "Password entering is different!!"
            }else if(TextUtils.isEmpty(binding.etPhoneNumber.text.toString())){
                binding.tvResultR.text = "Phone number is empty!!"
            }else if(TextUtils.isEmpty(binding.etEmail.text.toString())){
                binding.tvResultR.text = "Email is empty!!"
            }else if(binding.etPasswordR.length() < 6){
                binding.tvResultR.text = "Password must >= 6 characters!!"
            }else if(binding.etPhoneNumber.length() > 11){
                binding.tvResultR.text = "Phone number cannot > 11 numbers!!"
            }else{
                database.child("userList").child(binding.etUsernameR.text.toString()).child("username").get().addOnSuccessListener {
                    if (it.value.toString() == binding.etUsernameR.text.toString()){
                        binding.tvResultR.text = "Username is already exist!!"
                    }else {

                        val newUser = User(
                            binding.etUsernameR.text.toString(),
                            binding.etPasswordR.text.toString(),
                            Integer.parseInt(binding.etPhoneNumber.text.toString()),
                            binding.etEmail.text.toString()
                        )
                        userRegister(
                            binding.etEmail.text.toString(),
                            binding.etPasswordR.text.toString()
                        )
                        addNewUser(newUser)
                        val profileUpdates = UserProfileChangeRequest.Builder()
                            .setDisplayName(binding.etUsernameR.text.toString())// Set the full name as the display name
                            .build()

                        user?.updateProfile(profileUpdates)
                            ?.addOnCompleteListener { profileUpdateTask ->
                                if (profileUpdateTask.isSuccessful) {
                                    // User's display name has been updated
                                    // You can now store other user data (like email, phone number, etc.) in the database
                                } else {
                                    // Handle the failure to update the display name
                                }
                            }
                    }
                }
            }
        }
        binding.tvLoginPage.setOnClickListener() {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        //supportActionBar?.hide()
    }

    private fun addNewUser(newUser: User) {
        database.child("userList")
            .child(newUser.username).setValue(newUser)
            .addOnSuccessListener {
                binding.tvResultR.text = "User added!!!"
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
            }
            .addOnFailureListener { ex->
                binding.tvResultR.text = ex.message
            }
    }

    private fun userRegister(email: String, psw: String) {
        auth.createUserWithEmailAndPassword(email, psw)
            .addOnSuccessListener {
                binding.tvResultR.text = "Register Successfully!!!"
            }
            .addOnFailureListener{ ex->
                binding.tvResultR.text = ex.message
            }
    }

}