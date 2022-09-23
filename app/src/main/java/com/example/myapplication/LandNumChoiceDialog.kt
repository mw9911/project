package com.example.myapplication

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class LandNumChoiceDialog (context: Context) {
    private val dlg= Dialog(context)
    private lateinit var OKBtn :Button
    private lateinit var cancelBtn:Button
    private lateinit var editNum:EditText
    private lateinit var landText:TextView
    private lateinit var listener:LandNumChoiceDialogOKClickedListener
    private var playerNum=25
    private var landNum=playerNum

    fun start(content:String){
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dlg.setContentView(R.layout.activity_land_num_choice_dialog)
        dlg.setCancelable(false)
        var lange:String=""
        for(i in playerNum..25 step playerNum)lange=lange+i.toString()+" "
        landText=dlg.findViewById(R.id.land_text)
        landText.text="영역의 수를 입력하십시오 ($lange 중 입력)"
        editNum=dlg.findViewById(R.id.edit_num)
        OKBtn=dlg.findViewById(R.id.ok_btn)
        OKBtn.setOnClickListener {
            var chk=1
            landNum=(editNum.text.toString()).toInt()
            for(i in playerNum..25 step playerNum){
                if(i==landNum){
                    var rtn=playerNum.toString()+"/"+landNum.toString()
                    listener.onOKClicked(rtn)
                    dlg.dismiss()
                }
            }
        }
        cancelBtn=dlg.findViewById(R.id.cancel_btn)
        cancelBtn.setOnClickListener {
            dlg.dismiss()
        }
        dlg.show()
    }
    fun setOnOKClickedListener(listener:(String)->Unit){
        this.listener=object:LandNumChoiceDialogOKClickedListener{
            override fun onOKClicked(content: String) {
                listener(content)
            }
        }
    }
    interface LandNumChoiceDialogOKClickedListener{
        fun onOKClicked(content : String)
    }
}