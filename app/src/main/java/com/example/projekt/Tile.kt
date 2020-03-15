package com.example.projekt
import android.graphics.Canvas
import android.graphics.drawable.Drawable


abstract class Tile(var posX: Int, var posY: Int, private var tileSize: Int){
    var jointLeft = "none"
    var jointRight = "none"
    var jointTop = "none"
    var jointBottom = "none"
    abstract fun draw(canvas : Canvas,drawables: Drawables)
    fun draw(canvas : Canvas,drawable: Drawable?){
        drawable?.setBounds(posX*tileSize,posY*tileSize,(posX+1)*tileSize,(posY+1)*tileSize)
        drawable?.draw(canvas)
    }
    fun isFreeJoint(): Boolean {
        return (jointLeft=="free")||(jointRight=="free")||(jointTop=="free")||(jointBottom=="free")
    }

    fun isHittingBorder(limitX: Int, limitY:Int): Boolean{
        var boolean = false

        if(jointTop=="free"&&posY==0){
            jointTop="occupied"
            boolean=true
        }

        if (jointBottom=="free"&&posY==limitY-1){
            jointBottom="occupied"
            boolean=true
        }

        if(jointRight=="free"&&posX==limitX-1){
            jointRight="occupied"
            boolean=true
        }

        if(jointLeft=="free"&&posX==0){
            jointLeft="occupied"
            boolean=true
        }

        return boolean

    }

    fun freeJoint(): String {
        if(jointTop=="free")  return "top"
        if(jointBottom=="free")  return "bottom"
        if(jointLeft=="free")  return "left"
        if(jointRight=="free")  return "right"
        return "none"
    }
    fun getFreeJointX(): Int{
        return when {
            jointLeft=="free" -> posX-1
            jointRight=="free" -> posX+1
            jointTop=="free" -> posX
            jointBottom=="free" -> posX
            else -> 0
        }
    }
    fun getFreeJointY(): Int{
        return when {
            jointLeft=="free" -> posY
            jointRight=="free" -> posY
            jointTop=="free" -> posY-1
            jointBottom=="free" -> posY+1
            else -> 0
        }
    }
}

class Terrain(posX: Int, posY: Int, tileSize: Int) : Tile(posX, posY, tileSize) {
   override fun draw(canvas : Canvas,drawables: Drawables){
       val drawable = drawables.tileGrass
       super.draw(canvas,drawable)
   }
}

class RoadHorizontal(posX: Int, posY: Int, tileSize: Int) : Tile(posX, posY, tileSize) {
    init{
        jointLeft = "free"
        jointRight = "free"
    }
    override fun draw(canvas : Canvas,drawables: Drawables){
        val drawable = drawables.tileRoadHorizontal
        super.draw(canvas,drawable)
    }
}

class RoadVertical(posX: Int, posY: Int, tileSize: Int) : Tile(posX, posY, tileSize) {
    init{
        jointTop = "free"
        jointBottom = "free"
    }
    override fun draw(canvas : Canvas,drawables: Drawables){
        val drawable = drawables.tileRoadVertical
        super.draw(canvas,drawable)
    }
}

class RoadCrossing4(posX: Int, posY: Int, tileSize: Int) : Tile(posX, posY, tileSize) {
    init{
        jointLeft = "free"
        jointRight = "free"
        jointTop = "free"
        jointBottom = "free"
    }
    override fun draw(canvas : Canvas,drawables: Drawables){
        val drawable = drawables.tileRoadCrossing4
        super.draw(canvas,drawable)
    }
}

class RoadLeftTop(posX: Int, posY: Int, tileSize: Int) : Tile(posX, posY, tileSize) {
    init{
        jointLeft = "free"
        jointTop = "free"
    }
    override fun draw(canvas : Canvas,drawables: Drawables){
        val drawable = drawables.tileRoadLeftTop
        super.draw(canvas,drawable)
    }
}

class RoadRightTop(posX: Int, posY: Int, tileSize: Int) : Tile(posX, posY, tileSize) {
    init{
        jointRight = "free"
        jointTop = "free"
    }
    override fun draw(canvas : Canvas,drawables: Drawables){
        val drawable = drawables.tileRoadRightTop
        super.draw(canvas,drawable)
    }
}

class RoadLeftBottom(posX: Int, posY: Int, tileSize: Int) : Tile(posX, posY, tileSize) {
    init{
        jointLeft = "free"
        jointBottom = "free"
    }
    override fun draw(canvas : Canvas,drawables: Drawables){
        val drawable = drawables.tileRoadLeftBottom
        super.draw(canvas,drawable)
    }
}

class RoadRightBottom(posX: Int, posY: Int, tileSize: Int) : Tile(posX, posY, tileSize) {
    init{
        jointRight = "free"
        jointBottom = "free"
    }
    override fun draw(canvas : Canvas,drawables: Drawables){
        val drawable = drawables.tileRoadRightBottom
        super.draw(canvas,drawable)
    }
}