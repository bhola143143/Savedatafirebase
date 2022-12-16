package com.example.savedatafirebase


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.savedatafirebase.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.registerBtn.setOnClickListener {


            val firstName = binding.firstName.text.toString()
            val lastName = binding.lastName.text.toString()
            val age = binding.age.text.toString()
            val userName = binding.userName.text.toString()

            database = FirebaseDatabase.getInstance().getReference(getString(R.string.users))
            val user = User(firstName, lastName, age, userName)
            database.child(userName).setValue(user).addOnSuccessListener {

                binding.firstName.text?.clear()
                binding.lastName.text?.clear()
                binding.age.text?.clear()
                binding.userName.text?.clear()

                Toast.makeText(this, getString(R.string.Successfuly), Toast.LENGTH_SHORT).show()

            }.addOnFailureListener {

                Toast.makeText(this, getString(R.string.failed), Toast.LENGTH_SHORT).show()


            }


        }
    }
}
