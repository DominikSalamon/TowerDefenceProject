package com.example.projekt



class Camera(private var screenWidth: Int, private var screenHeight: Int, private var boardWidth: Int, private var boardHeight: Int){

    private var limit = 200f
    private var scale = 1f
    private val scaleMax = 3f
    private val scaleMin= 0.3f

    private var x = 0f
    private var y = 0f
    private var prevX = 0f
    private var prevY = 0f
    private var startX = 0f
    private var startY = 0f

    fun scaling(scaleFactor: Float){
        scale *= scaleFactor
        scale = scaleMin.coerceAtLeast(scale.coerceAtMost(scaleMax))
    }



    fun startPoint(x: Float, y: Float) {
        startX = x - prevX
        startY = y - prevY
    }

    fun move(X: Float, Y: Float){
        x=X-startX
        y=Y-startY

        val boardWidthScale=(boardWidth*scale).toInt()
        val boardHeightScale=(boardHeight*scale).toInt()

        val difX = boardWidthScale-screenWidth
        val difY = boardHeightScale-screenHeight

        val minX = -difX-limit
        val minY = -difY-limit

        if(difX>0){
            if(x>limit) x=limit
            if(x<minX) x=minX
        }
        else if(difX<0){
            if(x<0) x=0f
            if(x>screenWidth-boardWidthScale) x= (screenWidth-boardWidthScale).toFloat()
        }

        if(difY>0){
            if(y>limit) y=limit
            if(y<minY) y=minY
        }
        else if(difY<0){
            if(y<0) y=0f
            if(y>screenHeight-boardHeightScale) y= (screenHeight-boardHeightScale).toFloat()
        }



        prevX=x
        prevY=y
    }

    fun getX(): Float { return x }
    fun getY(): Float { return y }
    fun getScale(): Float{ return scale }
}