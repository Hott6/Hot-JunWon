package org.techtown.soptseminar

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.techtown.soptseminar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this, EmptyActivity2::class.java)
        binding.btnLogin.setOnClickListener(){
            Toast.makeText(this, "토스트 성공", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }


    }

}