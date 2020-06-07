package com.example.projekt


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.annotation.RequiresApi





lateinit var drawables: Drawables

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("ViewConstructor")
class Game(private val screenWidth: Int, private val screenHeight: Int, context: Context, extras: Bundle) : SurfaceView(context),
        SurfaceHolder.Callback {
    private var gameLoop: GameLoop

    private val tileSize = 150
    private var performance: Performance
    private val player: Player
    private val camera: Camera
    private val highlight: Highlight
    private val mapManager : MapManager
    private val buyMenu: BuyMenu
    private val enemyManager: EnemyManager
    private val towerManager: TowerManager
    private val attacksManager: AttacksManager
    private val ticker: Ticker
    private var gameover = false

    fun pause(){
        gameLoop.stopLoop()
    }


    init {
        val surfaceHolder = holder
        surfaceHolder.addCallback(this)
        gameLoop = GameLoop(this, surfaceHolder)

        isFocusable = true

        performance = Performance(context,gameLoop)

        towerManager = TowerManager()
        attacksManager = AttacksManager()
        ticker = Ticker()

        drawables = Drawables(context)
        buyMenu = BuyMenu(drawables,tileSize)
        player = Player(context,screenWidth)

        mapManager = MapManager(extras.get("idMap").toString(),resources,drawables,tileSize)
        highlight = Highlight(mapManager,drawables,tileSize)

        camera = Camera(screenWidth, screenHeight,mapManager)



        enemyManager = EnemyManager(drawables, mapManager.getEnemyWayPoints(),tileSize)
        enemyManager.setPlayer(player)

        towerManager.setEnemyManager(enemyManager)
        towerManager.setAttacksManager(attacksManager)

        buyMenu.setCustomer(player)
        buyMenu.setTowerManager(towerManager)

    }




   private val scaleListener = object : ScaleGestureDetector.SimpleOnScaleGestureListener() {

        override fun onScale(detector: ScaleGestureDetector): Boolean {

            camera.scaling(detector.scaleFactor)

            invalidate()
            return true
        }
    }

    private val mScaleDetector = ScaleGestureDetector(context, scaleListener)

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {

        mScaleDetector.onTouchEvent(event)

        when(event.action){
            MotionEvent.ACTION_DOWN -> {
                camera.startPoint(event.x,event.y)

                val realX = camera.getRealX(event.x)
                val realY = camera.getRealY(event.y)

                highlight.activate(realX,realY)


                buyMenu.show(highlight,realX,realY)


                towerManager.addTowers(buyMenu.getBoughtTowers())






                return true
            }
            MotionEvent.ACTION_MOVE -> {
                camera.move(event.x,event.y)
                return true
            }
        }


        return super.onTouchEvent(event)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        Log.d("Game.kt","surfaceCreated")

        gameLoop.startLoop()
    }

    override fun surfaceChanged(
        holder: SurfaceHolder,
        format: Int,
        width: Int,
        height: Int
    ) {
        Log.d("Game.kt","surfaceChanged")

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        Log.d("Game.kt","surfaceDestroyed")
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

            canvas.save()

            canvas.drawRGB(240, 240, 240)
            canvas.translate(camera.getX(), camera.getY())
            canvas.scale(camera.getScale(), camera.getScale())

            mapManager.drawMap(canvas)

            towerManager.drawTowers(canvas)
            enemyManager.drawEnemies(canvas)

            buyMenu.draw(canvas)
            highlight.draw(canvas)

            attacksManager.draw(canvas)

            canvas.restore()

            player.draw(canvas)
            performance.draw(canvas)

        if(gameover) {



            val drawable = drawables.gameOver
            drawable?.setBounds(screenWidth/2-300,screenHeight/2-300,screenWidth/2+300,screenHeight/2+300)
            drawable?.draw(canvas)

            val paint = Paint()
            paint.flags = Paint.ANTI_ALIAS_FLAG
            paint.color = Color.BLACK
            paint.textSize = 30f
            canvas.drawText("GAMEOVER", screenWidth/2-100f, screenHeight/2f, paint)


        }








    }

    @Synchronized fun update() {
            if(!gameover){
                ticker.update()


                towerManager.updateTowers()
                enemyManager.updateEnemies()
                attacksManager.updateAttacks()

                if(enemyManager.countEnemies()<1) enemyManager.spawnEnemy((Math.random()*3+2).toInt())

                if(player.health<=0) {
                    gameover=true
                }
            }







    }

}