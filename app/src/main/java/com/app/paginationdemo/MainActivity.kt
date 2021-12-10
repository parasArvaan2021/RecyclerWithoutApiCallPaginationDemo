package com.app.paginationdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnMainSubmit:Button=findViewById(R.id.btnMainSubmit)
        val etQuestion:EditText=findViewById(R.id.etMainQuestion)



        btnMainSubmit.setOnClickListener {
            if (etQuestion.text.toString().isNotEmpty()){
                val moveToNext=Intent(this,PaginationActivity::class.java)
                moveToNext.putExtra("QUESTION",etQuestion.text.toString().toInt())
                startActivity(moveToNext)
            }else
                Toast.makeText(this, "please enter value", Toast.LENGTH_SHORT).show()

        }
    }
}