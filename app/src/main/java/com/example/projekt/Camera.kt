package com.example.projekt

class Camera(private var difX: Int, private var difY: Int){
    private var x = 0
    private var y = 0
    private var prevX = 0
    private var prevY = 0
    private var startX = 0
    private var startY = 0
    private var limit = 200

    fun startPoint(x: Int, y: Int){
        startX=x-prevX
        startY=y-prevY
    }

    fun move(x: Int, y: Int){

        this.x=x-startX
        this.y=y-startY


        if(difX>0){
            if(this.x>limit) this.x = limit
            else if(this.x<-difX-limit+100) this.x = -difX-limit+100
        }
        else{
            if(this.x<0) this.x = 0

        }


        if(difY>0){
            if(this.y>limit) this.y = limit
            else if(this.y<-difY-limit) this.y = -difY-limit
        }
        else{

        }







        prevX=this.x
        prevY=this.y
    }

    fun getX(): Int { return x }
    fun getY(): Int { return y }
}