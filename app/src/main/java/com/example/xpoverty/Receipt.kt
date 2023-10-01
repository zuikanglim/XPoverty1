package com.example.xpoverty

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.xpoverty.databinding.ReceiptBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
private lateinit var auth: FirebaseAuth

class Receipt : AppCompatActivity() {
    private lateinit var binding : ReceiptBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ReceiptBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().getReference("UserDB")

        binding.tfTransactionNo.text = "ABC104"

        val intent = intent
        val str = intent.getStringExtra("typeOfBank")
        val str2 = intent.getStringExtra("accountNum")
        val str3 = intent.getStringExtra("amount")
        binding.tfBankName.text = str
        binding.tfAccountNo.text = str2
        binding.tfPayment.text = str3
        val username = auth.currentUser
        database.child("userList").child(username.toString()).get().addOnSuccessListener {
                binding.tfName.text = it.value.toString()
        }

        binding.btnReturn.setOnClickListener(){
            val intent = Intent(this, Account::class.java)
            startActivity(intent)
        }

        // calling the action bar
        var actionBar = supportActionBar

        // showing the back button in action bar
        if (actionBar != null) {
            actionBar.title = "Receipt"
            actionBar.setDisplayHomeAsUpEnabled(false)
        }
    }
}