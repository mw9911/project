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
import kotlinx.android.synthetic.main.activity_map.*
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
    val firstS:String="01/01/1@naver.com#######02/02/2@naver.com#######03/03/3@naver.com#######04/04/4@naver.com#######05/05/5@naver.com#######06/06/6@naver.com#######07/07/7@naver.com#######08/08/8@naver.com#######09/09/9@naver.com#######10/10/10@naver.com######11/11/11@naver.com######12/12/12@naver.com######13/13/13@naver.com######14/14/14@naver.com######15/15/15@naver.com######16/16/16@naver.com######17/17/17@naver.com######18/18/18@naver.com######19/19/19@naver.com######20/20/20@naver.com######21/21/21@naver.com######22/22/22@naver.com######23/23/23@naver.com######24/24/24@naver.com######25/25/25@naver.com######"
    val SPLITLEN: Int = 24
    val EMAILLEN : Int=20
    var userId:Int=1
    var landNum:Int=25
    var playerNum:Int=5
    var MAX_LANDNUM=25
    fun setMap(mapString: String,playerNum:Int,landNum:Int){
        var imgBtnArr = Array<ImageButton>(30){imgbtn_map1}
        imgBtnArr[1]=imgbtn_map1
        imgBtnArr[2]=imgbtn_map2
        imgBtnArr[3]=imgbtn_map3
        imgBtnArr[4]=imgbtn_map4
        imgBtnArr[5]=imgbtn_map5
        imgBtnArr[6]=imgbtn_map6
        imgBtnArr[7]=imgbtn_map7
        imgBtnArr[8]=imgbtn_map8
        imgBtnArr[9]=imgbtn_map9
        imgBtnArr[10]=imgbtn_map10
        imgBtnArr[11]=imgbtn_map11
        imgBtnArr[12]=imgbtn_map12
        imgBtnArr[13]=imgbtn_map13
        imgBtnArr[14]=imgbtn_map14
        imgBtnArr[15]=imgbtn_map15
        imgBtnArr[16]=imgbtn_map16
        imgBtnArr[17]=imgbtn_map17
        imgBtnArr[18]=imgbtn_map18
        imgBtnArr[19]=imgbtn_map19
        imgBtnArr[20]=imgbtn_map20
        imgBtnArr[21]=imgbtn_map21
        imgBtnArr[22]=imgbtn_map22
        imgBtnArr[23]=imgbtn_map23
        imgBtnArr[24]=imgbtn_map24
        imgBtnArr[25]=imgbtn_map25
        for(i in landNum+1..MAX_LANDNUM){
            imgBtnArr[i].visibility= View.INVISIBLE
        }
    }
    fun ShowMap(mapString:String,colorType:Int){
        var imgBtnArr = Array<ImageButton>(30){imgbtn_map1}
        imgBtnArr[1]=imgbtn_map1
        imgBtnArr[2]=imgbtn_map2
        imgBtnArr[3]=imgbtn_map3
        imgBtnArr[4]=imgbtn_map4
        imgBtnArr[5]=imgbtn_map5
        imgBtnArr[6]=imgbtn_map6
        imgBtnArr[7]=imgbtn_map7
        imgBtnArr[8]=imgbtn_map8
        imgBtnArr[9]=imgbtn_map9
        imgBtnArr[10]=imgbtn_map10
        imgBtnArr[11]=imgbtn_map11
        imgBtnArr[12]=imgbtn_map12
        imgBtnArr[13]=imgbtn_map13
        imgBtnArr[14]=imgbtn_map14
        imgBtnArr[15]=imgbtn_map15
        imgBtnArr[16]=imgbtn_map16
        imgBtnArr[17]=imgbtn_map17
        imgBtnArr[18]=imgbtn_map18
        imgBtnArr[19]=imgbtn_map19
        imgBtnArr[20]=imgbtn_map20
        imgBtnArr[21]=imgbtn_map21
        imgBtnArr[22]=imgbtn_map22
        imgBtnArr[23]=imgbtn_map23
        imgBtnArr[24]=imgbtn_map24
        imgBtnArr[25]=imgbtn_map25
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
        var imgBtnArr = Array<ImageButton>(30){imgbtn_map1}
        imgBtnArr[1]=imgbtn_map1
        imgBtnArr[2]=imgbtn_map2
        imgBtnArr[3]=imgbtn_map3
        imgBtnArr[4]=imgbtn_map4
        imgBtnArr[5]=imgbtn_map5
        imgBtnArr[6]=imgbtn_map6
        imgBtnArr[7]=imgbtn_map7
        imgBtnArr[8]=imgbtn_map8
        imgBtnArr[9]=imgbtn_map9
        imgBtnArr[10]=imgbtn_map10
        imgBtnArr[11]=imgbtn_map11
        imgBtnArr[12]=imgbtn_map12
        imgBtnArr[13]=imgbtn_map13
        imgBtnArr[14]=imgbtn_map14
        imgBtnArr[15]=imgbtn_map15
        imgBtnArr[16]=imgbtn_map16
        imgBtnArr[17]=imgbtn_map17
        imgBtnArr[18]=imgbtn_map18
        imgBtnArr[19]=imgbtn_map19
        imgBtnArr[20]=imgbtn_map20
        imgBtnArr[21]=imgbtn_map21
        imgBtnArr[22]=imgbtn_map22
        imgBtnArr[23]=imgbtn_map23
        imgBtnArr[24]=imgbtn_map24
        imgBtnArr[25]=imgbtn_map25
        var mapString=firstS
        var battleWinner:Int
        var battleLoser:Int
        var colorType=1
        if(intent.hasExtra("playerNum")) {
            playerNum = intent.getIntExtra("playerNum", 0)
        }
        if(intent.hasExtra("landNum")) {
            landNum = intent.getIntExtra("landNum", 0)
        }
        if(intent.hasExtra("start")){
            var tmpCnt =Array<Int>(30){0}
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
        }
        if(intent.hasExtra("mapString")){
            mapString=intent.getStringExtra("mapString").toString()
        }
        testButton1.setOnClickListener {
            userId= Integer.parseInt(testEdit1.text.toString())
            testText1.setText("Player : "+ testEdit1.text.toString())
            false
        }
        setMap(mapString,playerNum,landNum)
        ShowMap(mapString,colorType)
        color_change_btn.setOnClickListener {
            colorType=colorType%2+1
            if(colorType==1){
                color_change_btn.text="색 벗기기"
                ShowMap(mapString,colorType)
            }
            if(colorType==2){
                color_change_btn.text="색 입히기"
                ShowMap(mapString,colorType)
            }
        }
        attack_btn_intent.setOnClickListener {
            if(MapHandling().getLandCount(mapString,userId)==0){
                Toast.makeText(this,"영역이 존재하지 않아 공격 할 수 없습니다.",Toast.LENGTH_SHORT).show()
            }
            else {
                val intent = Intent(this, AtcMapActivity::class.java)
                intent.putExtra("userId", userId)
                intent.putExtra("mapString", mapString)
                intent.putExtra("playerNum",playerNum)
                intent.putExtra("landNum",landNum)
                startActivity(intent)
                finish()
            }
        }
    }
}

