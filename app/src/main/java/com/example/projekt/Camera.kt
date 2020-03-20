package com.example.projekt



class Camera(private var screenWidth: Int, private var screenHeight: Int, private var boardWidth: Int, private var boardHeight: Int){
    private var x = 0
    private var y = 0
    private var prevX = 0
    private var prevY = 0
    private var startX = 0
    private var startY = 0
    private var limit = 200
    var scale = 1f

    fun startPoint(x: Int, y: Int) {
        startX = x - prevX
        startY = y - prevY
    }

    fun move(X: Int, Y: Int){
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
            if(x<0) x=0
            if(x>screenWidth-boardWidthScale) x=screenWidth-boardWidthScale
        }

        if(difY>0){
            if(y>limit) y=limit
            if(y<minY) y=minY
        }
        else if(difY<0){
            if(y<0) y=0
            if(y>screenHeight-boardHeightScale) y=screenHeight-boardHeightScale
        }



        prevX=x
        prevY=y
    }

    fun getX(): Int { return x }
    fun getY(): Int { return y }
}