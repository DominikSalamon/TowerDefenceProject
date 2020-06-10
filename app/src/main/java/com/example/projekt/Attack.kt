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
            (attacker.getY()+25).toFloat(), (target.getX()+75).toFloat(),
            (target.getY()+50).toFloat(),paint)
    }
}



class Arrow(drawables: Drawables, private var angle:Float): Attack() {
    private val drawable = drawables.arrow
    private var posX = 0
    private var posY = 0
    private var tarX = 0
    private var tarY = 0

    override var damage = 30
    private var speed = 20

    private var t = true

    private fun move(){


        val a = posX-tarX
        val b = posY-tarY

        val c = abs(a) + abs(b)

        posX-=speed*a/c
        posY-=speed*b/c



        if(abs(b)<50&&abs(a)<50) {
            target.health-=damage
            ongoing = false
        }

    }
    override fun update(){
        super.update()

        if(t){
            posX = attacker.getX()
            posY = attacker.getY()
            tarX = target.getX()
            tarY = target.getY()

            t=false
        }

        move()


    }
    override fun draw(canvas: Canvas){
        super.draw(canvas)

        canvas.save()

        canvas.rotate(angle+90, 35+posX+10f, 35+posY+25f)

        drawable?.setBounds(70+posX,posY,70+posX+20,70+posY+50)
        drawable?.draw(canvas)

        canvas.restore()

    }
}


class Lava(drawables: Drawables,var posX: Int,var posY: Int): Attack() {
    private val drawable = drawables.lava

    private var duration = 10000 // millis
    private val killer = ticker.newTick(duration)

    override var damage = 100
    private var angle = 0

    init{
        angle = (0..360).random()
    }

    override fun update() {
        super.update()

        if(killer.get()){
            ongoing = false
        }
    }

    override fun draw(canvas: Canvas){
        super.draw(canvas)
        canvas.save()

        canvas.rotate(angle.toFloat(), (posX+75).toFloat(), (posY+75).toFloat())
        drawable?.setBounds(posX,posY,posX+150,posY+150)
        drawable?.draw(canvas)

        canvas.restore()


    }
}
