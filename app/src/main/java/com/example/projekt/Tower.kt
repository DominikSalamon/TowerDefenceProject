package com.example.projekt

import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.Log
import kotlin.math.pow
import kotlin.math.sqrt

open class Tower{
    var name = "Tower"
    private var x = 0
    private var y = 0
    var cost = 0
    var drawable: Drawable? = null
    var animatedDrawable: AnimatedDrawable? = null
    var attackRadius = 150
    private var attackInterval = 500// in millis
    private lateinit var attack: Laser
    private var ticker = Ticker()
    private var target: Enemy? = null

    private var reload = ticker.newTick(attackInterval)
    private var nextFrame = ticker.newTick(1000)


    fun update(enemyList: ArrayList<Enemy>, attacksManager: AttacksManager){
        ticker.update()

        if(nextFrame.get()) animatedDrawable?.update()

        target=null

        findEnemy(enemyList)

        if(attack())
            attacksManager.addAttack(attack)
    }

    private fun attack(): Boolean{
        if(target!=null){
            if(reload.get()) {
                attack = Laser()
                attack.setSides(this, target!!)

                return true
            }
        }

        return false
    }

    fun getX(): Int{
        return x
    }

    fun getY(): Int{
        return y
    }

    fun draw(canvas: Canvas){
        drawable = animatedDrawable?.getDrawable()
        drawable?.setBounds(x*100,y*100,x*100+100,y*100+100)
        drawable?.draw(canvas)

    }

    fun draw(canvas: Canvas,X: Int,Y:Int){
        drawable = animatedDrawable?.getDrawable()
        drawable?.setBounds(x*100+X,y*100+Y,x*100+100+X,y*100+100+Y)
        drawable?.draw(canvas)
    }

    fun isClicked(X: Int,Y: Int): Boolean{
        Log.d("Buymenu.kt","$x $y")
        return  X>=x*100&&
                X<=x*100+100&&
                Y>=y*100&&
                Y<=y*100+100

    }

    fun setPosition(X: Int, Y:Int){
        x = X
        y = Y
    }

    fun cost(): Int{
        return cost
    }

    fun colides(X: Int, Y: Int): Boolean {
        return x==X&&y==Y
    }

    fun copyContent(tower: Tower){
        name = tower.name
        drawable = tower.drawable
        cost = tower.cost
        attackRadius = tower.attackRadius
        animatedDrawable = tower.animatedDrawable
    }

    open fun copy(): Tower{
        return Tower()
    }

    private fun findEnemy(enemyList: ArrayList<Enemy>){
        enemyList.forEach{
            if(distance(it)<=attackRadius){
                target = it
                return
            }
        }
    }

    private fun distance(enemy: Enemy): Float {
        return sqrt((enemy.getX()-x*100 ).toFloat().pow(2)+(enemy.getY() - y*100 ).toFloat().pow(2))
    }
}



class TestTower : Tower {
    constructor(tower: Tower): super(){
       copyContent(tower)
    }

    constructor(drawables: Drawables): super(){
        name = "Tower1"
        drawable = drawables.towerTest
        cost = 100
        attackRadius = 200
        animatedDrawable = AnimatedDrawable(arrayOf(
            drawables.towerTest,
            drawables.towerTest2,
            drawables.towerTest3
        ))
    }

    override fun copy(): TestTower{
        return TestTower(this)
    }


}

class TestTower2 : Tower {
    constructor(tower: Tower): super(){
        copyContent(tower)
    }

    constructor(drawables: Drawables): super(){
        name = "Tower2"
        drawable = drawables.towerTest2
        cost = 500
        attackRadius = 100
        animatedDrawable = AnimatedDrawable(arrayOf(
            drawables.tower1,
            drawables.tower2,
            drawables.tower3
        ))
    }

    override fun copy(): TestTower2{
        return TestTower2(this)
    }


}

