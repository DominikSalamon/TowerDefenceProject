package com.example.projekt

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log


class BuyMenu(drawables: Drawables){
    private var drawable = drawables.buyMenu
    private var drawable2 = drawables.buyMenu2
    private var x = 0
    private var y = 0
    private var width = 500
    private var height = 200
    private var visible = false
    private var items = ArrayList<Tower>()
    private var hX = 0f
    private var hY = 0f
    private val boughtTowers = ArrayList<Tower>()
    private var customer: Player? = null
    private var towerManager: TowerManager? = null

    init{
        addItems(arrayOf(
            TestTower(drawables),
            TestTower2(drawables)
        ))
    }

    fun getBoughtTowers(): ArrayList<Tower>{
        val toReturn = ArrayList<Tower>()
        toReturn.addAll(boughtTowers)
        boughtTowers.clear()
        return toReturn
    }

    fun show(highlight: Highlight,realX: Int, realY: Int) {
        if(!isClicked(realX,realY)){
            if(highlight.doubleTapped&&highlight.isActive()) {
                show((highlight.getX() * 100).toInt() + 50, (highlight.getY() * 100).toInt())
                hX=highlight.getX()
                hY=highlight.getY()
            }
            else
                hide()
        }
        else{
            if(towerManager?.isPlaceFree(hX.toInt(),hY.toInt())!!){
                for(i in 0 until items.size){
                    if(items[i].isClicked(realX-getPosX1(),realY-getPosY1())){
                        Log.d("BuyMenu.kt","item isClicked")
                        customer?.buy(items[i].copy(),boughtTowers,hX.toInt(),hY.toInt())
                        break
                    }
                }
            }
        }
    }



    private fun show(X: Int, Y:Int) {
        x = X - width / 2
        y = Y - height
        visible = true
    }

    private fun hide() {
        visible = false
    }


    fun draw(canvas: Canvas) {
        if(visible){
            drawable2?.setBounds( getPosX1()-1, getPosY1()-1, getPosX2()+1, getPosY2()+1)
            drawable2?.draw(canvas)

            drawable?.setBounds( getPosX1(), getPosY1(), getPosX2(), getPosY2())
            drawable?.draw(canvas)




            for(i in 0 until items.size){
                items[i].draw(canvas,x,y)
                val paint = Paint()
                paint.flags = Paint.ANTI_ALIAS_FLAG
                paint.color = Color.BLACK
                paint.textSize = 30f
                canvas.drawText(items[i].name, (x+i*100+10).toFloat(), y.toFloat()+140, paint)
                canvas.drawText(items[i].cost.toString(), (x+i*100+10).toFloat(), y.toFloat()+180, paint)
            }
        }
    }

    private fun isClicked(X: Int, Y:Int): Boolean{
        return  isVisible()&&
                X>=getPosX1()&&
                X<=getPosX2()&&
                Y>=getPosY1()&&
                Y<=getPosY2()
    }

    private fun isVisible(): Boolean{
        return visible
    }

    private fun getPosX1(): Int{
        return x
    }

    private fun getPosY1(): Int {
        return y
    }

    private fun getPosX2(): Int {
        return x+width
    }

    private fun getPosY2(): Int {
        return y+height
    }

    private fun addItem(tower: Tower){
        tower.setPosition(items.size,0)
        items.add(tower)
    }

    private fun addItems(towers: Array<Tower>){
        for(tower in towers){
            addItem(tower)
        }
    }

    fun setCustomer(player: Player) {
        customer = player
    }
    fun setTowerManager(manager: TowerManager) {
        towerManager = manager
    }
}