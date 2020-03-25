package com.example.projekt
import android.graphics.Canvas
import android.graphics.drawable.Drawable


abstract class Tile(var posX: Int, var posY: Int, private var tileSize: Int){
    var jointLeft = "occupied"
    var jointRight = "occupied"
    var jointTop = "occupied"
    var jointBottom = "occupied"
    abstract fun draw(canvas : Canvas,drawables: Drawables)
    fun draw(canvas : Canvas,drawable: Drawable?){
        drawable?.setBounds(posX*tileSize,posY*tileSize,(posX+1)*tileSize,(posY+1)*tileSize)
        drawable?.draw(canvas)
    }
    fun draw(canvas: Canvas, drawable: Drawable?, camera: Camera){
        val leftPos = posX*tileSize + camera.getX()
        val topPos = posY*tileSize + camera.getY()
        val rightPos = (posX+1)*tileSize + camera.getX()
        val bottomPos = (posY+1)*tileSize + camera.getY()
        drawable?.setBounds(leftPos,topPos,rightPos,bottomPos)
        drawable?.draw(canvas)
    }
    fun drawRect(canvas : Canvas,drawable: Drawable?,tileWidth:Int){
        drawable?.setBounds(posX*tileSize,posY*tileSize,(posX+1)*tileWidth,(posY+1)*tileSize)
        drawable?.draw(canvas)
    }
    fun isFreeJoint(): Boolean {
        return (jointLeft=="free")||(jointRight=="free")||(jointTop=="free")||(jointBottom=="free")
    }
    fun freeJoints(): ArrayList<String>{
        val array = arrayListOf<String>()
        if(jointTop=="free")    array.add("top")
        if(jointBottom=="free") array.add("bottom")
        if(jointLeft=="free")   array.add("left")
        if(jointRight=="free")  array.add("right")
        return array
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

    fun getFreeJointX(joint: String): Int{
        return when(joint){
            "left" -> posX-1
            "right" -> posX+1
            "top" -> posX
            "bottom" -> posX
            else -> 0
        }
    }
    fun getFreeJointY(joint: String): Int{
        return when(joint){
            "left" -> posY
            "right" -> posY
            "top" -> posY-1
            "bottom" -> posY+1
            else -> 0
        }
    }
}

class Terrain(posX: Int, posY: Int, tileSize: Int) : Tile(posX, posY, tileSize) {
    override fun draw(canvas: Canvas, drawables: Drawables) {
        val drawable = drawables.tileGrass
        super.draw(canvas,drawable)
    }

    fun drawRect(canvas : Canvas,drawables: Drawables,width: Int){
        val drawable = drawables.tileGrass
        super.drawRect(canvas,drawable,width)
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

class RoadLeftTopRight(posX: Int, posY: Int, tileSize: Int) : Tile(posX, posY, tileSize) {
    init{
        jointLeft = "free"
        jointTop = "free"
        jointRight = "free"
    }
    override fun draw(canvas : Canvas,drawables: Drawables){
        val drawable = drawables.tileRoadLeftTopRight
        super.draw(canvas,drawable)
    }
}

class RoadTopRightBottom(posX: Int, posY: Int, tileSize: Int) : Tile(posX, posY, tileSize) {
    init{
        jointTop = "free"
        jointRight = "free"
        jointBottom = "free"
    }
    override fun draw(canvas : Canvas,drawables: Drawables){
        val drawable = drawables.tileRoadTopRightBottom
        super.draw(canvas,drawable)
    }
}

class RoadRightBottomLeft(posX: Int, posY: Int, tileSize: Int) : Tile(posX, posY, tileSize) {
    init{
        jointRight = "free"
        jointBottom = "free"
        jointLeft = "free"
    }
    override fun draw(canvas : Canvas,drawables: Drawables){
        val drawable = drawables.tileRoadRightBottomLeft
        super.draw(canvas,drawable)
    }
}

class RoadBottomLeftTop(posX: Int, posY: Int, tileSize: Int) : Tile(posX, posY, tileSize) {
    init{
        jointTop = "free"
        jointBottom = "free"
        jointLeft = "free"
    }
    override fun draw(canvas : Canvas,drawables: Drawables){
        val drawable = drawables.tileRoadBottomLeftTop
        super.draw(canvas,drawable)
    }
}


class Road(posX: Int, posY: Int, tileSize: Int) : Tile(posX, posY, tileSize) {
    override fun draw(canvas: Canvas, drawables: Drawables) {
        val drawable = drawables.tileRoad
        super.draw(canvas,drawable)
    }
}
