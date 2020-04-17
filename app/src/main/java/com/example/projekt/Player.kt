package com.example.projekt

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat

class Player(private val context: Context){
    private var health = 7
    private var score = 0
    private var money = 10000
    private var paint: Paint = Paint()

    init{
        paint.flags = Paint.ANTI_ALIAS_FLAG
        paint.color = ContextCompat.getColor(context, R.color.colorDark)
        paint.textSize = 40f
    }


    fun buy(tower: Tower, towerArray: ArrayList<Tower>, x: Int, y: Int){
        var colision = false

        for(i in 0 until towerArray.size){
            colision = towerArray[i].colides(x,y)
            if(colision){
                break
            }
        }

        if(!colision){
            if(money>=tower.cost()){
                money-=tower.cost()
                tower.setPosition(x,y)
                towerArray.add(tower)

            }
        }

    }


    fun buy(tower: Tower,towerArray: ArrayList<Tower>, highlight: Highlight){
        val x = highlight.getX().toInt()
        val y = highlight.getY().toInt()

        var colision = false

        for(i in 0 until towerArray.size){
            colision = towerArray[i].colides(x,y)
            if(colision){
                highlight.setActive(false)
                break
            }
        }



        if(highlight.isActive()){
            if(money>=tower.cost()){


                money-=tower.cost()
                tower.setPosition(x,y)
                towerArray.add(tower)


            }
        }
    }

    fun draw(canvas: Canvas,screenWidth: Int){

        val text = money.toString()

        canvas.drawRoundRect(screenWidth-251f,19f,screenWidth-19f,71f,26f,26f,paint)
        paint.color = ContextCompat.getColor(context, R.color.colorLight)
        canvas.drawRoundRect(screenWidth-250f,20f,screenWidth-20f,70f,25f,25f,paint)
        paint.color = ContextCompat.getColor(context, R.color.colorDark)
        paint.textSize = 30f
        canvas.drawText("Money: $text", screenWidth-230f, 55f, paint)

    }

}