package com.example.projekt

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import kotlin.math.*


abstract class Attack{
    abstract var damage: Int

    protected val ticker = Ticker()
    protected var ongoing = true

    lateinit var attacker: Tower
    lateinit var target: Enemy

    fun onGoing(): Boolean{
        return ongoing
    }

    open fun draw(canvas: Canvas){}
    open fun update() {
        ticker.update()
    }

    fun setSides(attacker: Tower, target: Enemy) {
        this.attacker = attacker
        this.target = target
    }

}

class Laser: Attack() {
    private val paint = Paint()
    private var duration = 100 // millis
    override var damage = 10
    private val killer = ticker.newTick(duration)
    init{
        paint.strokeWidth = 10f
        paint.color = Color.RED
    }

    override fun update() {
        super.update()

        if(killer.get()){
            target.health-=damage
            ongoing = false
        }
    }

    override fun draw(canvas: Canvas){
        super.draw(canvas)
        canvas.drawLine((attacker.getX()+50).toFloat(),
            (attacker.getY()+50).toFloat(), (target.getX()+50).toFloat(),
            (target.getY()+50).toFloat(),paint)
    }
}

class Bullet(drawables: Drawables, private var angle:Float): Attack() {
    private val drawable = drawables.bullet
    private var posX = 0
    private var posY = 0

    override var damage = 30
    private var speed = 10
    private var vX = 0f
    private var vY = 0f
    private var t = true

    private fun move(){

        vX=cos(angle)*speed
        vY=sin(angle)*speed

        posX+=vX.toInt()
        posY+=vY.toInt()

    }
    override fun update(){
        super.update()

        if(t){
            posX = attacker.getX()
            posY = attacker.getY()

            t=false
        }

        move()
    }
    override fun draw(canvas: Canvas){
        super.draw(canvas)
        drawable?.setBounds(posX,posY,posX+10,posY+30)
        drawable?.draw(canvas)

    }
}
