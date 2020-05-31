package com.example.projekt

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint


class EnemyManager(private val drawables: Drawables, private val wayPoints:  Array<IntArray>,private val tileSize: Int){
    private val enemyList = ArrayList<Enemy>()
    private lateinit var player: Player
    fun setPlayer(player: Player){
        this.player = player
    }
    fun spawnEnemy(a: Int){
        val x = wayPoints[0][0]
        val y = wayPoints[0][1]

        val targetX = wayPoints[1][0]
        val targetY = wayPoints[1][1]


        val enemy = Enemy(drawables,tileSize)

        enemy.setPosition(x*tileSize,y*tileSize)
        enemy.setTarget(targetX*tileSize,targetY*tileSize)
        enemy.step = a

        enemyList.add(enemy)
    }

    fun drawEnemies(canvas: Canvas){
        for(i in 0 until enemyList.size){
            enemyList[i].draw(canvas)
        }
    }

    fun getEnemyList(): ArrayList<Enemy> {
        return enemyList
    }

    @Synchronized fun updateEnemies(){

        val toDelete = arrayListOf<Enemy>()

        for(enemy in enemyList){
            enemy.update()

            for(i in 0 until enemy.step){
                if(enemy.reachedTarget()){

                    enemy.targetIndex++

                    if(enemy.targetIndex<wayPoints.size){

                        val targetX = wayPoints[enemy.targetIndex][0]
                        val targetY = wayPoints[enemy.targetIndex][1]
                        enemy.setTarget(targetX*tileSize,targetY*tileSize)

                    }
                    else {

                        player.money-=100
                        player.health-=10
                        toDelete.add(enemy)
                    }

                }

                enemy.move()
            }

            if(enemy.health<=0){
                player.money+=100
                toDelete.add(enemy)
            }

        }


        enemyList.removeAll(toDelete)



    }

    fun countEnemies(): Int {
        return enemyList.size
    }
}

class Enemy(drawables: Drawables,private val tileSize: Int){
    val  drawable = drawables.enemy
    private var animatedDrawable = AnimatedDrawable(arrayOf(
        drawables.enemy,
        drawables.enemy1
    ))

    private var x = 0
    private var y = 0
    var health = 100
    var step = 2 //pixel per update
    private var targetX = 0
    private var targetY = 0
    var targetIndex = 1
    private val paint = Paint()
    fun getX(): Int {
        return x
    }
    fun getY(): Int {
        return y
    }


    fun setPosition(X: Int, Y: Int){
        x=X
        y=Y
    }

    fun draw(canvas: Canvas){
       // drawable?.setBounds(x, y,x+tileSize,y+tileSize)
       // drawable?.draw(canvas)

        animatedDrawable.getDrawable().setBounds(x, y,x+tileSize,y+tileSize)
        animatedDrawable.getDrawable().draw(canvas)

        paint.strokeWidth = 5F
        paint.color = Color.RED
        canvas.drawLine(x.toFloat(), y.toFloat(),x+health.toFloat(), y.toFloat(),paint)
    }
    fun update(){
        animatedDrawable.update()
    }

    fun reachedTarget(): Boolean{
        val a = targetX-x
        val b = targetY-y
        return a==0&&b==0
    }

    fun move(){
        val vX = targetX-x
        val vY = targetY-y

        if(vX>0)
            x++
        else
            x--

        if(vY>=0)
            y++
        else
            y--

    }

    fun setTarget(X: Int, Y: Int){
        targetX=X
        targetY=Y
    }

}