package com.example.projekt

import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.Log

open class Tower{
    private var x = 0
    private var y = 0
    var cost = 0
    var drawable: Drawable? = null
    var attackRadius = 0
    lateinit var animatedDrawable: AnimatedDrawable



    fun draw(canvas: Canvas){
        drawable = animatedDrawable.getDrawable()
        drawable?.setBounds(x*100,y*100,x*100+100,y*100+100)
        drawable?.draw(canvas)
    }

    fun draw(canvas: Canvas,X: Int,Y:Int){
        drawable = animatedDrawable.getDrawable()
        drawable?.setBounds(x*100+X,y*100+Y,x*100+100+X,y*100+100+Y)
        drawable?.draw(canvas)
    }

    fun isClicked(X: Int,Y: Int): Boolean{
        Log.d("Buymenyu.kt","$x $y")
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

    open fun copy(): Tower{
        return Tower()
    }

}

class TestTower : Tower {
    constructor(tower: Tower): super(){
        drawable = tower.drawable
        cost = tower.cost
        attackRadius = tower.attackRadius
        animatedDrawable = tower.animatedDrawable
    }

    constructor(drawables: Drawables): super(){
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
        drawable = tower.drawable
        cost = tower.cost
        attackRadius = tower.attackRadius
        animatedDrawable = tower.animatedDrawable
    }

    constructor(drawables: Drawables): super(){
        drawable = drawables.towerTest2
        cost = 500
        attackRadius = 100
        animatedDrawable = AnimatedDrawable(arrayOf(
            drawables.tower1,
            drawables.tower2,
            drawables.tower3
        ))
    }

    override fun copy(): TestTower{
        return TestTower(this)
    }


}