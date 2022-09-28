package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_atc_map.*
import kotlinx.android.synthetic.main.activity_map.*
import kotlinx.android.synthetic.main.activity_map.color_change_btn
import kotlinx.android.synthetic.main.activity_map.imgbtn_map1
import kotlinx.android.synthetic.main.activity_map.imgbtn_map10
import kotlinx.android.synthetic.main.activity_map.imgbtn_map11
import kotlinx.android.synthetic.main.activity_map.imgbtn_map12
import kotlinx.android.synthetic.main.activity_map.imgbtn_map13
import kotlinx.android.synthetic.main.activity_map.imgbtn_map14
import kotlinx.android.synthetic.main.activity_map.imgbtn_map15
import kotlinx.android.synthetic.main.activity_map.imgbtn_map16
import kotlinx.android.synthetic.main.activity_map.imgbtn_map17
import kotlinx.android.synthetic.main.activity_map.imgbtn_map18
import kotlinx.android.synthetic.main.activity_map.imgbtn_map19
import kotlinx.android.synthetic.main.activity_map.imgbtn_map2
import kotlinx.android.synthetic.main.activity_map.imgbtn_map20
import kotlinx.android.synthetic.main.activity_map.imgbtn_map21
import kotlinx.android.synthetic.main.activity_map.imgbtn_map22
import kotlinx.android.synthetic.main.activity_map.imgbtn_map23
import kotlinx.android.synthetic.main.activity_map.imgbtn_map24
import kotlinx.android.synthetic.main.activity_map.imgbtn_map25
import kotlinx.android.synthetic.main.activity_map.imgbtn_map3
import kotlinx.android.synthetic.main.activity_map.imgbtn_map4
import kotlinx.android.synthetic.main.activity_map.imgbtn_map5
import kotlinx.android.synthetic.main.activity_map.imgbtn_map6
import kotlinx.android.synthetic.main.activity_map.imgbtn_map7
import kotlinx.android.synthetic.main.activity_map.imgbtn_map8
import kotlinx.android.synthetic.main.activity_map.imgbtn_map9
import java.security.SecureRandom
import kotlin.random.Random as Random

class MapActivity : AppCompatActivity() {
    val colorArr=arrayOf(R.color.color1,R.color.color1,R.color.color2,R.color.color3,R.color.color4,R.color.color5,
        R.color.color6,R.color.color7,R.color.color8,R.color.color9,R.color.color10,
        R.color.color11,R.color.color12,R.color.color13,R.color.color14,R.color.color15,
        R.color.color16,R.color.color17,R.color.color18,R.color.color19,R.color.color20,
        R.color.color21,R.color.color22,R.color.color23,R.color.color24,R.color.color25)
   val SPLITLEN: Int = 36
    val EMAILLEN : Int=20
    var userId:Int=1
    var landNum:Int=25
    var playerNum:Int=5
    var MAX_LANDNUM=25
    var mapString=""
    fun setMap(landNum:Int){
        val imgBtnArr = arrayOf(imgbtn_map1,imgbtn_map1,imgbtn_map2,imgbtn_map3,imgbtn_map4,imgbtn_map5,imgbtn_map6,imgbtn_map7,imgbtn_map8,imgbtn_map9,imgbtn_map10,
            imgbtn_map11,imgbtn_map12,imgbtn_map13,imgbtn_map14,imgbtn_map15,imgbtn_map16,imgbtn_map17,imgbtn_map18,imgbtn_map19,imgbtn_map20,
            imgbtn_map21,imgbtn_map22,imgbtn_map23,imgbtn_map24,imgbtn_map25)
        for(i in 1..landNum){
            imgBtnArr[i].visibility= View.VISIBLE
        }
    }
    fun showMap(mapString:String,colorType:Int){
        val imgBtnArr = arrayOf(imgbtn_map1,imgbtn_map1,imgbtn_map2,imgbtn_map3,imgbtn_map4,imgbtn_map5,imgbtn_map6,imgbtn_map7,imgbtn_map8,imgbtn_map9,imgbtn_map10,
            imgbtn_map11,imgbtn_map12,imgbtn_map13,imgbtn_map14,imgbtn_map15,imgbtn_map16,imgbtn_map17,imgbtn_map18,imgbtn_map19,imgbtn_map20,
            imgbtn_map21,imgbtn_map22,imgbtn_map23,imgbtn_map24,imgbtn_map25)
        if(colorType==1) {
            for (i in 1..landNum) {
                imgBtnArr[i].setColorFilter(
                    ContextCompat.getColor(
                        this,
                        colorArr[MapHandling().getMapColorToIdx(mapString, i)]
                    )
                )
            }
        }
        if(colorType==2) {
            for (i in 1..landNum) {
                imgBtnArr[i].setColorFilter(
                    colorArr[MapHandling().getMapColorToIdx(mapString, i)],
                    PorterDuff.Mode.DST
                )
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        val imgBtnArr = arrayOf(imgbtn_map1,imgbtn_map1,imgbtn_map2,imgbtn_map3,imgbtn_map4,imgbtn_map5,imgbtn_map6,imgbtn_map7,imgbtn_map8,imgbtn_map9,imgbtn_map10,
            imgbtn_map11,imgbtn_map12,imgbtn_map13,imgbtn_map14,imgbtn_map15,imgbtn_map16,imgbtn_map17,imgbtn_map18,imgbtn_map19,imgbtn_map20,
            imgbtn_map21,imgbtn_map22,imgbtn_map23,imgbtn_map24,imgbtn_map25)
        var colorType=1
        for(i in 1 .. 25){
            imgBtnArr[i].visibility=View.INVISIBLE
        }
        val db= FirebaseDatabase.getInstance().getReference("test")
        db.child("ttt").get().addOnSuccessListener {
            landNum=it.child("landNum").value.toString().toInt()
            playerNum=it.child("playerNum").value.toString().toInt()
            mapString=it.child("mapString").value.toString()
            userId=it.child("userId").value.toString().toInt()
            if(intent.hasExtra("start")){
                val tmpCnt =Array<Int>(30){0}
                for(i in 1..26){
                    tmpCnt[i]=0
                }
                val secureRandom=SecureRandom()
                var tmpIdx : Int
                for(i in playerNum+1..landNum){
                    while(true){
                        tmpIdx=secureRandom.nextInt(playerNum)+1
                        if(tmpCnt[tmpIdx]<(landNum/playerNum)-1){
                            mapString=MapHandling().UpdateMap(mapString,tmpIdx,i)
                            tmpCnt[tmpIdx]++
                            break
                        }
                    }
                }
                db.child("ttt").child("mapString").setValue(mapString)
            }
            setMap(landNum)
            showMap(mapString,colorType)
            color_change_btn.setOnClickListener {
                colorType=colorType%2+1
                if(colorType==1){
                    color_change_btn.text="색 벗기기"
                    showMap(mapString,colorType)
                }
                if(colorType==2){
                    color_change_btn.text="색 입히기"
                    showMap(mapString,colorType)
                }
            }
            attack_btn_intent.setOnClickListener {
                if (MapHandling().getLandCount(mapString, userId) == 0) {
                    Toast.makeText(this, "영역이 존재하지 않아 공격 할 수 없습니다.", Toast.LENGTH_SHORT).show()
                } else { val intent = Intent(this, AtcMapActivity::class.java)
                            intent.putExtra("userId", userId)
                    intent.putExtra("mapString", mapString)
                    intent.putExtra("playerNum", playerNum)
                    intent.putExtra("landNum", landNum)
                    startActivity(intent)
                    overridePendingTransition(0,0)
                    finish()
                }
            }
        }
    }
}


