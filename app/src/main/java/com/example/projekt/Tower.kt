package com.example.projekt

import android.graphics.Canvas
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



class MagicTower(private val drawables: Drawables) : Tower() {
    override var name = "Magic"
    override var cost = 300
    override var radius = 200
    override var interval = 150
    override var animatedDrawable = AnimatedDrawable(arrayOf(
        drawables.magicTower1,
        drawables.magicTower2,
        drawables.magicTower3
    ))

    override var reload = ticker.newTick(interval)

    override fun getAttack(): Attack {
        return Laser()
    }

    override fun getClone(): MagicTower{
        return MagicTower(drawables)
    }

}

class ArcherTower(private val drawables: Drawables) : Tower() {
    override var name =  "Archer"
    override var cost = 400
    override var radius = 300
    override var interval = 900
    override var animatedDrawable = AnimatedDrawable(arrayOf(
        drawables.archerTower1
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
       return Arrow(drawables,topAngle)
    }

    override fun update(enemyList: ArrayList<Enemy>, attacksManager: AttacksManager){
        if(target!=null) topAngle = calcAngle()

        super.update(enemyList, attacksManager)
    }

    override fun draw(canvas: Canvas){
        super.draw(canvas)

        canvas.save()
        canvas.rotate(topAngle+90, getX().toFloat()+75, getY().toFloat()+75)
        topDrawable?.setBounds(getX(),getY(),getX()+150,getY()+150)
        topDrawable?.draw(canvas)
        canvas.restore()

    }

    override fun getClone(): ArcherTower{
        return ArcherTower(drawables)
    }

}


class VulcanTower(private val drawables: Drawables) : Tower() {
    override var radius = 300
    override var name =  "Vulcan"
    override var cost = 1000
    override var interval = 5000
    override var animatedDrawable = AnimatedDrawable(arrayOf(
        drawables.vulcanTower1,
        drawables.vulcanTower2
    ))


    override var reload = ticker.newTick(interval)

    override fun getAttack(): Attack {
        return Lava(drawables,0,0)
    }

    private fun randPos(): Int{
        return (-radius..radius).random()
    }


    override fun update(enemyList: ArrayList<Enemy>, attacksManager: AttacksManager){
        ticker.update()
        animatedDrawable.update()

        if(reload.get()) {

            for(i in 0..4){
                attacksManager.addAttack(Lava(drawables,getX()+randPos(),getY()+randPos()))
            }

        }
    }

    override fun getClone(): VulcanTower{
        return VulcanTower(drawables)
    }

}



