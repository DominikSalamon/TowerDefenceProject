package com.example.projekt

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint


class AttacksManager{
    private val attackList = ArrayList<Attack>()

    fun draw(canvas: Canvas){
        attackList.forEach{
            it.draw(canvas)
        }
    }

    fun updateAttacks(){
        val toDelete = arrayListOf<Attack>()

        for(attack in attackList){
           attack.update()

            if(!attack.onGoing()){

                toDelete.add(attack)
            }


        }


        attackList.removeAll(toDelete)


    }

    fun addAttack(attack: Attack) {
        attackList.add(attack)
    }


}


open class Attack{
    val paint = Paint()
    var damage = 10f
    var x1 = 0f
    var x2 = 0f
    var y1 = 0f
    var y2 = 0f

    private val duration = 100 // millis

    private val ticker = Ticker()
    private val killer = ticker.newTick(duration)
    private var ongoing = true

    private lateinit var attacker: Tower
    private lateinit var target: Enemy

    fun onGoing(): Boolean{
        return ongoing
    }

    open fun draw(canvas: Canvas){
    }
    fun update() {
        ticker.update()

        if(killer.get()){
            target.health-=damage.toInt()
            ongoing = false
        }




        x1 = attacker.getX()*100f
        y1 = attacker.getY()*100f

        x2 = target.getX().toFloat()
        y2 = target.getY().toFloat()

    }

    fun setSides(attacker: Tower, target: Enemy) {
        this.attacker = attacker
        this.target = target
    }


}

class Laser: Attack() {
    init{
        damage = 50f
    }



    override fun draw(canvas: Canvas){
        super.draw(canvas)
        paint.strokeWidth = 20f
        paint.color = Color.RED
        canvas.drawLine(x1+50,y1+50,x2+50,y2+50,paint)
    }


}