package com.example.myapplication

import android.annotation.SuppressLint
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
import kotlinx.android.synthetic.main.activity_atc_map.imgbtn_map1
import kotlinx.android.synthetic.main.activity_atc_map.imgbtn_map10
import kotlinx.android.synthetic.main.activity_atc_map.imgbtn_map11
import kotlinx.android.synthetic.main.activity_atc_map.imgbtn_map12
import kotlinx.android.synthetic.main.activity_atc_map.imgbtn_map13
import kotlinx.android.synthetic.main.activity_atc_map.imgbtn_map14
import kotlinx.android.synthetic.main.activity_atc_map.imgbtn_map15
import kotlinx.android.synthetic.main.activity_atc_map.imgbtn_map16
import kotlinx.android.synthetic.main.activity_atc_map.imgbtn_map17
import kotlinx.android.synthetic.main.activity_atc_map.imgbtn_map18
import kotlinx.android.synthetic.main.activity_atc_map.imgbtn_map19
import kotlinx.android.synthetic.main.activity_atc_map.imgbtn_map2
import kotlinx.android.synthetic.main.activity_atc_map.imgbtn_map20
import kotlinx.android.synthetic.main.activity_atc_map.imgbtn_map21
import kotlinx.android.synthetic.main.activity_atc_map.imgbtn_map22
import kotlinx.android.synthetic.main.activity_atc_map.imgbtn_map23
import kotlinx.android.synthetic.main.activity_atc_map.imgbtn_map24
import kotlinx.android.synthetic.main.activity_atc_map.imgbtn_map25
import kotlinx.android.synthetic.main.activity_atc_map.imgbtn_map3
import kotlinx.android.synthetic.main.activity_atc_map.imgbtn_map4
import kotlinx.android.synthetic.main.activity_atc_map.imgbtn_map5
import kotlinx.android.synthetic.main.activity_atc_map.imgbtn_map6
import kotlinx.android.synthetic.main.activity_atc_map.imgbtn_map7
import kotlinx.android.synthetic.main.activity_atc_map.imgbtn_map8
import kotlinx.android.synthetic.main.activity_atc_map.imgbtn_map9

class AtcMapActivity : AppCompatActivity() {
    val colorArr=arrayOf(R.color.red_color,R.color.green_color,R.color.orange_color)

    var attackMapString:String="0100100110100101001011000"
    var userId=1
    var mapString:String=""
    var playerNum :Int=1
    var landNum:Int=1
    fun landColorTypeCheck(mapString:String, attackMapString: String,userId: Int,chkIdx:Int) : Int{
        val attackIm=attackMapString.chunked(1)
        val tmp= Integer.parseInt(attackIm[chkIdx-1])

        if(MapHandling().getMapColorToIdx(mapString,userId)==MapHandling().getMapColorToIdx(mapString,chkIdx))
            return 2
        if(tmp==1)
            return 0
        return 1
    }
    fun setMap(){
        val imgBtnArr = arrayOf(imgbtn_map1,imgbtn_map2,imgbtn_map3,imgbtn_map4,imgbtn_map5,imgbtn_map6,imgbtn_map7,imgbtn_map8,imgbtn_map9,imgbtn_map10,
            imgbtn_map11,imgbtn_map12,imgbtn_map13,imgbtn_map14,imgbtn_map15,imgbtn_map16,imgbtn_map17,imgbtn_map18,imgbtn_map19,imgbtn_map20,
            imgbtn_map21,imgbtn_map22,imgbtn_map23,imgbtn_map24,imgbtn_map25)
        for(i in landNum+1 .. 25){
            imgBtnArr[i].visibility=View.INVISIBLE
        }
    }
    fun showAtcMap(mapString:String, attackMapString : String, userId:Int, colorType:Int){
        val landColorType= Array<Int>(30){0}
        val imgBtnArr = arrayOf(imgbtn_map1,imgbtn_map2,imgbtn_map3,imgbtn_map4,imgbtn_map5,imgbtn_map6,imgbtn_map7,imgbtn_map8,imgbtn_map9,imgbtn_map10,
            imgbtn_map11,imgbtn_map12,imgbtn_map13,imgbtn_map14,imgbtn_map15,imgbtn_map16,imgbtn_map17,imgbtn_map18,imgbtn_map19,imgbtn_map20,
            imgbtn_map21,imgbtn_map22,imgbtn_map23,imgbtn_map24,imgbtn_map25)
        if(colorType==1) {
            for(curIdx in 1..25) {
                landColorType[curIdx]=landColorTypeCheck(mapString,attackMapString,userId,curIdx)
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
        setContentView(R.layout.activity_atc_map)
        val db=FirebaseDatabase.getInstance().getReference("test")
        val imgBtnArr = arrayOf(imgbtn_map1,imgbtn_map2,imgbtn_map3,imgbtn_map4,imgbtn_map5,imgbtn_map6,imgbtn_map7,imgbtn_map8,imgbtn_map9,imgbtn_map10,
            imgbtn_map11,imgbtn_map12,imgbtn_map13,imgbtn_map14,imgbtn_map15,imgbtn_map16,imgbtn_map17,imgbtn_map18,imgbtn_map19,imgbtn_map20,
            imgbtn_map21,imgbtn_map22,imgbtn_map23,imgbtn_map24,imgbtn_map25)
        var colorType=1
        for(i in 1..25){
            imgBtnArr[i].setOnClickListener(AttackMapButtonListener())
        }
        db.child("ttt").get().addOnSuccessListener {
            userId = it.child("userId").value.toString().toInt()
            mapString = it.child("mapString").value.toString()
            playerNum = it.child("playerNum").value.toString().toInt()
            landNum = it.child("landNum").value.toString().toInt()
            setMap()
            showAtcMap(mapString, attackMapString, userId, colorType)
            color_change_btn.setOnClickListener {
                colorType=colorType%2+1
                if(colorType==1){
                    color_change_btn.text="색 벗기기"
                    showAtcMap(mapString,attackMapString,userId,colorType)
                }
                if(colorType==2){
                    color_change_btn.text="색 입히기"
                    showAtcMap(mapString,attackMapString,userId,colorType)
                }
            }
            back_btn_intent.setOnClickListener {
                val intent= Intent(this, MapActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }
    inner class AttackMapButtonListener : View.OnClickListener{
        override fun onClick(v:View?) {
            val intent= Intent(this@AtcMapActivity, GameActivity::class.java)
            val bIntent= Intent(this@AtcMapActivity, MapActivity::class.java)
            val dlg = AttackChoiceDialog(this@AtcMapActivity)
            var imgNum:Int
            imgNum=0
            val db=FirebaseDatabase.getInstance().getReference("test")
            dlg.setOnOKClickedListener {
                Toast.makeText(this@AtcMapActivity,"공격하였습니다",Toast.LENGTH_SHORT).show()

                db.child("ttt").child("opUserId").setValue(imgNum)
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
            val landTypeNum=landColorTypeCheck(mapString,attackMapString,userId,imgNum)
            if(landTypeNum==0){
                Toast.makeText(this@AtcMapActivity,"현재 전투 중 입니다.",Toast.LENGTH_SHORT).show()
                startActivity(bIntent)
                finish()
            }else if(landTypeNum==1){
                dlg.start("공격하시겠습니까?")
            }
            else{
                Toast.makeText(this@AtcMapActivity,"본인 땅은 공격할 수 없습니다.",Toast.LENGTH_SHORT).show()
                startActivity(bIntent)
                finish()
            }
        }
    }

}