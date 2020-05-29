package com.example.projekt

import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.Log
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.pow
import kotlin.math.sqrt

abstract class Tower{
    private val tileSize: Int = 150
    private var x: Int = 0
    private var y: Int = 0

    abstract var animatedDrawable: AnimatedDrawable

    abstract var name: String
    abstract var cost: Int
    abstract var radius: Int
    abstract var interval: Int

    protected var ticker = Ticker()
    abstract var reload: Tick

    var target: Enemy? = null

    open fun update(enemyList: ArrayList<Enemy>, attacksManager: AttacksManager){
        ticker.update()
        animatedDrawable.update()

        target=null
        findEnemy(enemyList)

        if(target!=null){
            if(reload.get()) {
                val attack = getAttack()
                attack.setSides(this, target!!)
                attacksManager.addAttack(attack)
            }
        }


    }

    abstract fun getAttack(): Attack

    fun getX(): Int{
        return x
    }

    fun getY(): Int{
        return y
    }
    open fun draw(canvas: Canvas){
        animatedDrawable.getDrawable().setBounds(x,y,x+tileSize,y+tileSize)
        animatedDrawable.getDrawable().draw(canvas)
    }

    fun draw(canvas: Canvas,X: Int,Y:Int){
        animatedDrawable.getDrawable().setBounds(x+X,y+Y,x+tileSize+X,y+tileSize+Y)
        animatedDrawable.getDrawable().draw(canvas)
    }

    fun isClicked(X: Int,Y: Int): Boolean{
        return  X>=x&&
                X<=x+tileSize&&
                Y>=y&&
                Y<=y+tileSize

    }

    fun setPosition(X: Int, Y:Int){
        x = X*tileSize
        y = Y*tileSize
    }

    fun cost(): Int{
        return cost
    }

    fun collides(X: Int, Y: Int): Boolean {
        return x==X&&y==Y
    }

    open fun getClone(): Tower{
        return this
    }

    private fun findEnemy(enemyList: ArrayList<Enemy>){
        enemyList.forEach{
            if(distance(it)<=radius){
                target = it
                return
            }
        }
    }

    private fun distance(enemy: Enemy): Float {
        return sqrt((enemy.getX()-x ).toFloat().pow(2)+(enemy.getY() - y ).toFloat().pow(2))
    }
}



class TestTower(private val drawables: Drawables) : Tower() {
    override var name = "Tower1"
    override var cost = 100
    override var radius = 300
    override var interval = 100
    override var animatedDrawable = AnimatedDrawable(arrayOf(
        drawables.towerTest,
        drawables.towerTest2,
        drawables.towerTest3
    ))

    override var reload = ticker.newTick(interval)

    override fun getAttack(): Attack {
        return Laser()
    }

    override fun getClone(): TestTower{
        return TestTower(drawables)
    }

}

class TestTower2(private val drawables: Drawables) : Tower() {
    override var name =  "Tower2"
    override var cost = 500
    override var radius = 200
    override var interval = 500
    override var animatedDrawable = AnimatedDrawable(arrayOf(
        drawables.tower1,
        drawables.tower2
    ))

    private var topDrawable = drawables.towerTop
    private var topAngle = 0f

    override var reload = ticker.newTick(interval)

    private fun calcAngle(): Float{
        if(target!=null){
            return (atan2(target!!.getY() - getY().toFloat(), target!!.getX() - getX().toFloat()) * 180 / PI).toFloat()
        }
        return 0f
    }

    override fun getAttack(): Attack {
       return Bullet(drawables,topAngle)
    }

    override fun update(enemyList: ArrayList<Enemy>, attacksManager: AttacksManager){
        super.update(enemyList, attacksManager)

        if(target!=null) topAngle = calcAngle()

    }

    override fun draw(canvas: Canvas){
        super.draw(canvas)

        canvas.save()
        canvas.rotate(topAngle+90, getX().toFloat()+50, getY().toFloat()+50)
        topDrawable?.setBounds(getX(),getY(),getX()+100,getY()+100)
        topDrawable?.draw(canvas)
        canvas.restore()

    }

    override fun getClone(): TestTower2{
        return TestTower2(drawables)
    }

}

