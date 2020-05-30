package com.example.projekt

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat

class Player(private val context: Context,private val screenWidth: Int){
    var money = 1000
    var health = 100
    private var paint: Paint = Paint()

    init{
        paint.flags = Paint.ANTI_ALIAS_FLAG
        paint.color = ContextCompat.getColor(context, R.color.colorDark)
        paint.textSize = 40f
    }


    fun buy(tower: Tower, towerArray: ArrayList<Tower>, x: Int, y: Int){
        if(money>=tower.cost()){
            money-=tower.cost()
            tower.setPosition(x,y)
            towerArray.add(tower)
        }
    }


    fun draw(canvas: Canvas){

        val text = money.toString()

        canvas.drawRoundRect(screenWidth-251f,19f,screenWidth-19f,71f,26f,26f,paint)
        paint.color = ContextCompat.getColor(context, R.color.colorLight)
        canvas.drawRoundRect(screenWidth-250f,20f,screenWidth-20f,70f,25f,25f,paint)
        paint.color = ContextCompat.getColor(context, R.color.colorDark)
        paint.textSize = 30f
        canvas.drawText("Money: $text", screenWidth-230f, 55f, paint)

    }

}