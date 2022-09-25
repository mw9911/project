package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val intent= Intent(this, ChoiceMapActivity::class.java)
        val db= FirebaseDatabase.getInstance().getReference("test")
        var opUserId:Int
        var userId:Int
        var mapString:String
        db.child("ttt").get().addOnSuccessListener {
            opUserId = it.child("opUserId").value.toString().toInt()
            userId = it.child("userId").value.toString().toInt()
            mapString = it.child("mapString").value.toString()
            val ran:Int=(10*Math.random()).toInt()

            intent.putExtra("mapString",mapString)
            var win:Int=userId
            var lose:Int=opUserId
            if(ran==1){
                win=opUserId
                lose=userId
            }
            db.child("ttt").child("winnerId").setValue(win)
            db.child("ttt").child("loserId").setValue(lose)
            startActivity(intent)
            finish()
        }

    }
}