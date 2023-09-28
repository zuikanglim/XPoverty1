package com.example.xpoverty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.xpoverty.databinding.ReceiptBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Receipt : AppCompatActivity() {
    private lateinit var binding : ReceiptBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ReceiptBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().getReference("UserDB")

        binding.tfTransactionNo.setText("ABC104")

        val intent = intent
        val str = intent.getStringExtra("typeOfBank")
        val str2 = intent.getStringExtra("accountNum")
        val str3 = intent.getStringExtra("amount")
        binding.tfBankName.setText(str)
        binding.tfAccountNo.setText(str2)
        binding.tfPayment.setText(str3)
        database.child("userList").child("loongxi").child("username").get().addOnSuccessListener {
                binding.tfName.text = it.value.toString()
        }

        binding.btnReturn.setOnClickListener(){
            val intent = Intent(this, Account::class.java)
            startActivity(intent)
        }

        // calling the action bar
        var actionBar = getSupportActionBar()

        // showing the back button in action bar
        if (actionBar != null) {
            actionBar.setTitle("Receipt")
            actionBar.setDisplayHomeAsUpEnabled(false)
        }
    }
}