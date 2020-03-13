package com.example.projekt
import android.graphics.Canvas


abstract class Tile(protected var posX: Int, protected var posY: Int,protected var tileSize: Int){
   abstract fun draw(canvas : Canvas,drawables: Drawables)
}

class Terrain(posX: Int, posY: Int, tileSize: Int) : Tile(posX, posY, tileSize) {
   override fun draw(canvas : Canvas,drawables: Drawables){
       val drawable = drawables.tileGrass
       drawable?.setBounds(posX*tileSize,posY*tileSize,(posX+1)*tileSize,(posY+1)*tileSize)
       drawable?.draw(canvas)
   }
}

class RoadHorizontal(posX: Int, posY: Int, tileSize: Int) : Tile(posX, posY, tileSize) {
    override fun draw(canvas : Canvas,drawables: Drawables){
        val drawable = drawables.tileRoadHorizontal
        drawable?.setBounds(posX*tileSize,posY*tileSize,(posX+1)*tileSize,(posY+1)*tileSize)
        drawable?.draw(canvas)
    }
}

class RoadVertical(posX: Int, posY: Int, tileSize: Int) : Tile(posX, posY, tileSize) {
    override fun draw(canvas : Canvas,drawables: Drawables){
        val drawable = drawables.tileRoadVertical
        drawable?.setBounds(posX*tileSize,posY*tileSize,(posX+1)*tileSize,(posY+1)*tileSize)
        drawable?.draw(canvas)
    }
}
class RoadCrossing4(posX: Int, posY: Int, tileSize: Int) : Tile(posX, posY, tileSize) {
    override fun draw(canvas : Canvas,drawables: Drawables){
        val drawable = drawables.tileRoadCrossing4
        drawable?.setBounds(posX*tileSize,posY*tileSize,(posX+1)*tileSize,(posY+1)*tileSize)
        drawable?.draw(canvas)
    }
}