package com.example.savedatafirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.savedatafirebase.databinding.ActivityReadDataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ReadDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReadDataBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.readdataBtn.setOnClickListener {

            val userName: String = binding.etusername.text.toString()
            if (userName.isNotEmpty()) {

                readData(userName)

            } else {

                Toast.makeText(this, getString(R.string.enterusername), Toast.LENGTH_SHORT).show()

            }

        }

    }

    private fun readData(userName: String) {

        database = FirebaseDatabase.getInstance().getReference(getString(R.string.users))
        database.child(userName).get().addOnSuccessListener {

            if (it.exists()) {

                val firstname = it.child(getString(R.string.firstname)).value
                val lastName = it.child(getString(R.string.lastname)).value
                val age = it.child(getString(R.string.age1)).value
                Toast.makeText(this, getString(R.string.Successfuly), Toast.LENGTH_SHORT).show()
                binding.etusername.text?.clear()
                binding.tvFirstName.text = firstname.toString()
                binding.tvLastName.text = lastName.toString()
                binding.tvAge.text = age.toString()

            } else {

                Toast.makeText(this, getString(R.string.exist), Toast.LENGTH_SHORT).show()


            }

        }.addOnFailureListener {

            Toast.makeText(this, getString(R.string.failed), Toast.LENGTH_SHORT).show()


        }


    }
}