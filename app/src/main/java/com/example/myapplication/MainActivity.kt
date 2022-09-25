package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val db=FirebaseDatabase.getInstance().getReference("test")
       // db.child("ttt").child("name").setValue("조현우")
        db.child("ttt").get().addOnSuccessListener {
            Toast.makeText(this,it.child("name").value.toString(),Toast.LENGTH_SHORT).show()

        }
        btn_map_intent.setOnClickListener {
            val intent= Intent(this,WaitActivity::class.java)
            db.child("ttt").child("playerNum").setValue(e_t.text.toString().toInt())
            db.child("ttt").child("userId").setValue(user_edit.text.toString().toInt())
            startActivity(intent)
        }
    }
}