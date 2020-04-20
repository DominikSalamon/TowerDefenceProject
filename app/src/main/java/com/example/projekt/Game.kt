package com.example.projekt


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("ViewConstructor")
class Game(private val screenWidth: Int,screenHeight: Int,context: Context, extras: Bundle) : SurfaceView(context),
        SurfaceHolder.Callback {
    private var gameLoop: GameLoop

    private var performance: Performance
    private val drawables: Drawables
    private val player: Player
    private val camera: Camera
    private val highlight: Highlight
    private val mapManager : MapManager
    private val buyMenu: BuyMenu
    private val enemyManager: EnemyManager
    private val towerManager: TowerManager
    private val attacksManager: AttacksManager

    private val ticker: Ticker



    fun pause(){
        Log.d("Game.kt","pause()")
        gameLoop.stopLoop()
    }


    init {
        Log.d("Game.kt","Game init")
        val surfaceHolder = holder
        surfaceHolder.addCallback(this)
        gameLoop = GameLoop(this, surfaceHolder)
        isFocusable = true

        performance = Performance(context,gameLoop)
        drawables = Drawables(context)
        player = Player(context)

        mapManager = MapManager(extras.get("idMap").toString(),resources,drawables)

        camera = Camera(screenWidth, screenHeight,mapManager.getMapWidth(),mapManager.getMapHeight())



        enemyManager = EnemyManager(drawables, mapManager.getEnemyWayPoints())
        enemyManager.setPlayer(player)

        towerManager = TowerManager()
        attacksManager = AttacksManager()

        towerManager.setEnemyManager(enemyManager)
        towerManager.setAttacksManager(attacksManager)

        ticker = Ticker()

        buyMenu = BuyMenu(drawables)
        buyMenu.setCustomer(player)
        buyMenu.setTowerManager(towerManager)

        highlight = Highlight(mapManager,drawables)

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

        holder.addCallback(this)
        gameLoop = GameLoop(this, holder)
        performance = Performance(context,gameLoop)
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


        canvas.drawRGB(240,240,240)
        canvas.translate(camera.getX(),camera.getY())
        canvas.scale(camera.getScale(),camera.getScale())


        mapManager.drawMap(canvas)


        towerManager.drawTowers(canvas)
        enemyManager.drawEnemies(canvas)

        buyMenu.draw(canvas)
        highlight.draw(canvas)

        attacksManager.draw(canvas)

        canvas.restore()


        player.draw(canvas,screenWidth)
        performance.draw(canvas)
    }

    fun update() {
        ticker.update()

        enemyManager.updateEnemies()
        towerManager.updateTowers()

       if(enemyManager.countEnemies()<5) enemyManager.spawnEnemy((Math.random()*9+1).toInt())

        attacksManager.updateAttacks()
    }

}