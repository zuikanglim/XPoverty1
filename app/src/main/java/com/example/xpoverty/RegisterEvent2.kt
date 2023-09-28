package com.example.xpoverty

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.xpoverty.databinding.ActivityRegisterEvent2Binding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterEvent2 : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterEvent2Binding
    private lateinit var database: DatabaseReference
    private lateinit var database2: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterEvent2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().getReference("Event")
        database2 = FirebaseDatabase.getInstance().getReference("UserDB")

        binding.btnAttend2.setOnClickListener() {
            database2.child("userList").child(binding.etYourName.text.toString()).child("username").get().addOnSuccessListener { it1 ->
                if (it1.value.toString() != binding.etYourName.text.toString()) {
                    binding.tvResultRF.text = "Username is not a user!!"
                } else {
                    database2.child("userList").child(binding.etYourName.text.toString()).child("phoneNumber").get().addOnSuccessListener { it2 ->
                        if (it2.value.toString() != binding.etYourContactNo.text.toString()) {
                            binding.tvResultRF.text = "Phone is not valid!!"
                        } else {
                            val newSet = NameList(binding.etYourName.text.toString(),Integer.parseInt(it2.value.toString()))
                            database.child("Name List").child("event2").child(newSet.username).setValue(newSet).addOnSuccessListener{}
                                .addOnFailureListener { ex ->
                                    binding.tvResultRF.text = ex.message
                                }
                            binding.tvResultRF.text ="Register Successfully!!!"
                            val intent = Intent(this, Event2::class.java)
                            startActivity(intent)
                        }
                    }
                }
            }
        }
    }
}
