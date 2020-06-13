package com.example.projekt

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class BuyMenu(drawables: Drawables,private var tileSize:Int){
    private var drawable = drawables.buyMenu
    private var drawable2 = drawables.buyMenu2
    private var x = 0
    private var y = 0
    private var width = 450
    private var height = 300
    private var visible = false
    private var sellMenuVisibility = false
    private var items = ArrayList<Tower>()
    private var hX = 0f
    private var hY = 0f
    private val boughtTowers = ArrayList<Tower>()
    private lateinit var customer: Player
    private lateinit var towerManager: TowerManager

    init{
        addItems(arrayOf(
            MagicTower(drawables),
            ArcherTower(drawables),
            VulcanTower(drawables)
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
            hideSellMenu()
            hide()

            if(highlight.doubleTapped&&highlight.isActive()) {
                if(towerManager.isPlaceFree(hX.toInt()*tileSize,hY.toInt()*tileSize)){

                    show((highlight.getX() * tileSize).toInt() + 75, (highlight.getY() * tileSize).toInt())
                }
                else {

                    showSellMenu((highlight.getX() * tileSize).toInt() + 75, (highlight.getY() * tileSize).toInt())
                }

            }

            hX=highlight.getX()
            hY=highlight.getY()
        }
        else{
            if(sellMenuVisibility){
                if(realY>getPosY1()&&realY<getPosY2()){
                    if(realX<getPosX1()+width/2){
                        val tower = towerManager.findTower(hX.toInt()*tileSize,hY.toInt()*tileSize)
                        if (tower != null) {
                            customer.sell(tower,towerManager)
                        }
                    }
                    hideSellMenu()
                }
            }
            else{
                if(towerManager.isPlaceFree(hX.toInt()*tileSize,hY.toInt()*tileSize)){
                    for(i in 0 until items.size){
                        if(items[i].isClicked(realX-getPosX1(),realY-getPosY1())){
                            customer.buy(items[i].getClone(),boughtTowers,hX.toInt(),hY.toInt())
                            break
                        }
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

    private fun showSellMenu(X: Int, Y:Int) {
        x = X - width / 2
        y = Y - height
        sellMenuVisibility = true
    }

    private fun hide() {
        visible = false

    }
    private fun hideSellMenu() {
        sellMenuVisibility= false

    }



    fun draw(canvas: Canvas) {
        if(sellMenuVisibility){
            drawable2?.setBounds( getPosX1()-1, getPosY1()-1, getPosX2()+1, getPosY2()+1)
            drawable2?.draw(canvas)

            drawable?.setBounds( getPosX1(), getPosY1(), getPosX2(), getPosY2())
            drawable?.draw(canvas)

            val paint = Paint()
            paint.flags = Paint.ANTI_ALIAS_FLAG
            paint.color = Color.BLACK
            paint.textSize = 60f
            canvas.drawText("Sell", getPosX1()+20f, getPosY1()+80f, paint)

            paint.textSize = 40f
            canvas.drawText("Yes", getPosX1()+20f, getPosY1()+height/1.5f, paint)
            canvas.drawText("No", getPosX1()+width/1.5f, getPosY1()+height/1.5f, paint)

        }

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
                canvas.drawText(items[i].name, (x+i*tileSize+10).toFloat(), y.toFloat()+220, paint)
                canvas.drawText(items[i].cost.toString(), (x+i*tileSize+10).toFloat(), y.toFloat()+260, paint)
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
        return visible||sellMenuVisibility
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