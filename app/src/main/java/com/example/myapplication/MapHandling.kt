package com.example.myapplication

import android.graphics.Color
import kotlinx.android.synthetic.main.activity_map.*

class MapHandling {
    val SPLITLEN=24
    val EMAILLEN=18
    fun setMap(){
        var playerNum:Int
    }
    fun getAttractMapString(){

    }
    fun getUserColorToIdx(mapString:String,idx:Int) : Int{
        var tmpIdx=idx-1
        var tmpUserToken=mapString.chunked(SPLITLEN)
        var tmpUserIm=tmpUserToken[tmpIdx].split("/")
        var rtn= Integer.parseInt(tmpUserIm[1])
        return rtn
    }
    fun getMapColorToIdx(mapString:String,idx:Int):Int{
        var tmpIdx=idx-1
        var tmpUserToken=mapString.chunked(SPLITLEN)
        var tmpUserIm=tmpUserToken[tmpIdx].split("/")
        return Integer.parseInt(tmpUserIm[0])

    }
    fun getUserEmailToIdx(mapString:String,idx:Int):String{
        var tmpIdx=idx-1
        var tmpUserToken=mapString.chunked(SPLITLEN)
        var tmpUserIm=tmpUserToken[tmpIdx].split("/","#")
        return tmpUserIm[2]
    }
    fun UpdateMap(mapString:String,winnerNum:Int,loserNum:Int):String{
        var rtn:String=""
        var tmpUserToken=mapString.chunked(SPLITLEN)
        var tmpWinIdx=winnerNum-1
        var tmpLoserIdx=loserNum-1
        var tmpWinIm=tmpUserToken[tmpWinIdx].split("/")
        var tmpLoseIm=tmpUserToken[tmpLoserIdx].split("/")
        var tmpE:String =getUserEmailToIdx(mapString,winnerNum)+ "##################"
        var tmpUserIm=(tmpWinIm[0]+"/"+tmpLoseIm[1]+"/"+tmpE).chunked(SPLITLEN)
        var tmpidx=0
        while(tmpidx<25) {
            if (tmpidx != tmpLoserIdx)
                rtn = rtn + tmpUserToken[tmpidx]
            else
                rtn = rtn + tmpUserIm[0]
            tmpidx++
        }
        return rtn

    }
    fun getLandCount(mapString: String,idx:Int) : Int{
        var rtn=0
        for(i in 1..25){
            if(getUserColorToIdx(mapString,idx)==getMapColorToIdx(mapString,i))
                rtn++
        }

        return rtn
    }
}