package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_wait.*

class WaitActivity : AppCompatActivity() {
    val firstS:String="01/01/01@naver.com##################02/02/2@naver.com###################03/03/03@naver.com##################04/04/4@naver.com###################05/05/05@naver.com##################06/06/06@naver.com##################07/07/07@naver.com##################08/08/08@naver.com##################09/09/09@naver.com##################10/10/10@naver.com##################11/11/11@naver.com##################12/12/12@naver.com##################13/13/13@naver.com##################14/14/14@naver.com##################15/15/15@naver.com##################16/16/16@naver.com##################17/17/17@naver.com##################18/18/18@naver.com##################19/19/19@naver.com##################20/20/20@naver.com##################21/21/21@naver.com##################22/22/22@naver.com##################23/23/23@naver.com##################24/24/24@naver.com##################25/25/25@naver.com##################"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wait)

        val db= FirebaseDatabase.getInstance().getReference("test")
        db.child("ttt").child("mapString").setValue(firstS)
        btn_map_intent.setOnClickListener (WaitMapButtonListener())
    }
    inner class WaitMapButtonListener : View.OnClickListener{
        override fun onClick(v: View?) {
            val intent= Intent(this@WaitActivity, MapActivity::class.java)
            val dlg = LandNumChoiceDialog(this@WaitActivity)
            dlg.setOnOKClickedListener {content->
                intent.putExtra("start","")
                startActivity(intent)
            }
            //예 클릭시 실행
            dlg.start()
        }
    }
}