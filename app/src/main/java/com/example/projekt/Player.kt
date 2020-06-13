package com.example.projekt

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
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

    fun sell(tower: Tower, towerManager: TowerManager){
        money+=tower.cost/2
        towerManager.removeTower(tower)
    }


    fun draw(canvas: Canvas){

        var text = money.toString()


        paint.color = ContextCompat.getColor(context, R.color.gold)
        canvas.drawRoundRect(screenWidth-250f,20f,screenWidth-20f,70f,25f,25f,paint)
        paint.color = ContextCompat.getColor(context, R.color.colorDark)
        paint.textSize = 30f
        canvas.drawText("Money: $text", screenWidth-230f, 55f, paint)


        paint.color = ContextCompat.getColor(context, R.color.lightRed)
        canvas.drawRoundRect(20f,20f,20+health*3f,70f,25f,25f,paint)

        paint.color = ContextCompat.getColor(context, R.color.colorDark)
        text = health.toString()
        canvas.drawText("Health: $text", 40f, 55f, paint)
    }

}