package org.techtown.soptseminar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.techtown.soptseminar.databinding.ActivityEmpty2Binding
import org.techtown.soptseminar.databinding.ActivityMainBinding
class EmptyActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityEmpty2Binding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityEmpty2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this, MainActivity::class.java)
        binding.btnLogin.setOnClickListener(){
            Toast.makeText(this, "토스트성공", Toast.LENGTH_SHORT).show()

            startActivity(intent)
        }


    }

}