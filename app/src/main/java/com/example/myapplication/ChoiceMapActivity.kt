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
import kotlinx.android.synthetic.main.activity_choice_map.*
import kotlinx.android.synthetic.main.activity_choice_map.color_change_btn
import kotlinx.android.synthetic.main.activity_choice_map.imgbtn_map1
import kotlinx.android.synthetic.main.activity_choice_map.imgbtn_map10
import kotlinx.android.synthetic.main.activity_choice_map.imgbtn_map11
import kotlinx.android.synthetic.main.activity_choice_map.imgbtn_map12
import kotlinx.android.synthetic.main.activity_choice_map.imgbtn_map13
import kotlinx.android.synthetic.main.activity_choice_map.imgbtn_map14
import kotlinx.android.synthetic.main.activity_choice_map.imgbtn_map15
import kotlinx.android.synthetic.main.activity_choice_map.imgbtn_map16
import kotlinx.android.synthetic.main.activity_choice_map.imgbtn_map17
import kotlinx.android.synthetic.main.activity_choice_map.imgbtn_map18
import kotlinx.android.synthetic.main.activity_choice_map.imgbtn_map19
import kotlinx.android.synthetic.main.activity_choice_map.imgbtn_map2
import kotlinx.android.synthetic.main.activity_choice_map.imgbtn_map20
import kotlinx.android.synthetic.main.activity_choice_map.imgbtn_map21
import kotlinx.android.synthetic.main.activity_choice_map.imgbtn_map22
import kotlinx.android.synthetic.main.activity_choice_map.imgbtn_map23
import kotlinx.android.synthetic.main.activity_choice_map.imgbtn_map24
import kotlinx.android.synthetic.main.activity_choice_map.imgbtn_map25
import kotlinx.android.synthetic.main.activity_choice_map.imgbtn_map3
import kotlinx.android.synthetic.main.activity_choice_map.imgbtn_map4
import kotlinx.android.synthetic.main.activity_choice_map.imgbtn_map5
import kotlinx.android.synthetic.main.activity_choice_map.imgbtn_map6
import kotlinx.android.synthetic.main.activity_choice_map.imgbtn_map7
import kotlinx.android.synthetic.main.activity_choice_map.imgbtn_map8
import kotlinx.android.synthetic.main.activity_choice_map.imgbtn_map9
import kotlinx.android.synthetic.main.activity_main.*

class ChoiceMapActivity : AppCompatActivity() {
    var mapString:String=""
    val colorArr=arrayOf(R.color.red_color,R.color.green_color)
    var loserId:Int=0
    var winnerId:Int=0
    var playerNum:Int=1
    var landNum:Int=1
    fun landColorTypeCheck(mapString:String,userId: Int,chkIdx:Int) : Int{
        if(MapHandling().getMapColorToIdx(mapString,userId)==MapHandling().getMapColorToIdx(mapString,chkIdx)) return 1
        return 0
    }
    fun setMap(){
        val imgBtnArr = Array<ImageButton>(30){imgbtn_map1}
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
        for(i in landNum+1 .. 25){
            imgBtnArr[i].visibility=View.INVISIBLE
        }
    }
    fun showChoiceMap(colorType:Int){
        val landColorType= Array<Int>(30){0}
        val imgBtnArr = Array<ImageButton>(30){imgbtn_map1}
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
            for(curIdx in 1..25) {
                landColorType[curIdx]=landColorTypeCheck(mapString,loserId,curIdx)
            }
            for(i in 1..25){
                imgBtnArr[i].setColorFilter(ContextCompat.getColor(this,colorArr[landColorType[i]]))
                //imgBtnArr[i].setBackgroundColor(Color.parseColor(colorArr[landColorType[i]]))
            }
        }
        if(colorType==2) {
            for (i in 1..landNum) {
                imgBtnArr[i].setColorFilter(
                    colorArr[1],
                    PorterDuff.Mode.DST
                )
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice_map)
        val imgBtnArr = Array<ImageButton>(30){imgbtn_map1}
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
        val db=FirebaseDatabase.getInstance().getReference("test")
        db.child("ttt").get().addOnSuccessListener {
            loserId = it.child("loserId").value.toString().toInt()
            winnerId = it.child("winnerId").value.toString().toInt()
            mapString = it.child("mapString").value.toString()
            playerNum = it.child("playerNum").value.toString().toInt()
            landNum = it.child("landNum").value.toString().toInt()
            var colorType = 1
            setMap()
            showChoiceMap(colorType)
            color_change_btn.setOnClickListener {
                colorType = colorType % 2 + 1
                if (colorType == 1) {
                    color_change_btn.text = "색 벗기기"
                    showChoiceMap(colorType)
                }
                if (colorType == 2) {
                    color_change_btn.text = "색 입히기"
                    showChoiceMap(colorType)
                }
            }
            for (i in 1..25) {
                imgBtnArr[i].setOnClickListener(ChoiceMapButtonListener())
            }
        }
    }
    inner class ChoiceMapButtonListener : View.OnClickListener{
        override fun onClick(v:View?) {
            val intent= Intent(this@ChoiceMapActivity, MapActivity::class.java)
            val dlg = AttackChoiceDialog(this@ChoiceMapActivity)
            var imgNum:Int
            imgNum=0
            val db= FirebaseDatabase.getInstance().getReference("test")
            dlg.setOnOKClickedListener {
                Toast.makeText(this@ChoiceMapActivity,"선택하였습니다.",Toast.LENGTH_SHORT).show()
                mapString=MapHandling().UpdateMap(mapString,winnerId,imgNum)
                db.child("ttt").child("mapString").setValue(mapString)
                startActivity(intent)
                finish()
            }//예 클릭시 실행
            when(v?.id){
                R.id.imgbtn_map1->imgNum=1
                R.id.imgbtn_map2->imgNum=2
                R.id.imgbtn_map3->imgNum=3
                R.id.imgbtn_map4->imgNum=4
                R.id.imgbtn_map5->imgNum=5
                R.id.imgbtn_map6->imgNum=6
                R.id.imgbtn_map7->imgNum=7
                R.id.imgbtn_map8->imgNum=8
                R.id.imgbtn_map9->imgNum=9
                R.id.imgbtn_map10->imgNum=10
                R.id.imgbtn_map11->imgNum=11
                R.id.imgbtn_map12->imgNum=12
                R.id.imgbtn_map13->imgNum=13
                R.id.imgbtn_map14->imgNum=14
                R.id.imgbtn_map15->imgNum=15
                R.id.imgbtn_map16->imgNum=16
                R.id.imgbtn_map17->imgNum=17
                R.id.imgbtn_map18->imgNum=18
                R.id.imgbtn_map19->imgNum=19
                R.id.imgbtn_map20->imgNum=20
                R.id.imgbtn_map21->imgNum=21
                R.id.imgbtn_map22->imgNum=22
                R.id.imgbtn_map23->imgNum=23
                R.id.imgbtn_map24->imgNum=24
                R.id.imgbtn_map25->imgNum=25
            }
            val landTypeNum=landColorTypeCheck(mapString,loserId,imgNum)
            if(landTypeNum==1){
                dlg.start("선택하시겠습니까?")
            }else {
                Toast.makeText(this@ChoiceMapActivity,"상대가 가진 땅이 아닙니다. 다시 선택하십시오",Toast.LENGTH_SHORT).show()
            }
        }
    }
}