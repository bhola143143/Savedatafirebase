package com.example.savedatafirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.savedatafirebase.databinding.ActivityDeleteDataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDeleteDataBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteDataBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnDelete.setOnClickListener {
            val username = binding.etusername.text.toString()
            if (username.isNotEmpty())
                deleteData(username)
            else {
                Toast.makeText(this, getString(R.string.enterusername), Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun deleteData(username: String) {
        database = FirebaseDatabase.getInstance().getReference(getString(R.string.users))
        database.child(username).removeValue().addOnSuccessListener {

            binding.etusername.text?.clear()
            Toast.makeText(this, getString(R.string.successfulydeleted), Toast.LENGTH_SHORT).show()

        }.addOnFailureListener {
            Toast.makeText(this, getString(R.string.failed), Toast.LENGTH_SHORT).show()
        }

    }
}