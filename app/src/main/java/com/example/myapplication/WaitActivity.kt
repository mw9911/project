package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_wait.*

class WaitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wait)

        btn_map_intent.setOnClickListener (WaitMapButtonListener())
    }
    inner class WaitMapButtonListener : View.OnClickListener{
        override fun onClick(v: View?) {
            val intent= Intent(this@WaitActivity, MapActivity::class.java)
            val dlg = LandNumChoiceDialog(this@WaitActivity)
            dlg.setOnOKClickedListener {content->
                var token=content.split("/")
                intent.putExtra("playerNum",token[0].toInt())
                intent.putExtra("landNum",token[1].toInt())
                intent.putExtra("start","")
                startActivity(intent)
            }
            //예 클릭시 실행
            dlg.start("영역 수?")
        }
    }
}