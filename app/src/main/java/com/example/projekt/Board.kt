package com.example.projekt
import android.content.Context
import android.graphics.Canvas
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources



class Drawables(context: Context){
    val tileRoadHorizontal = AppCompatResources.getDrawable(context, R.drawable.tile_road_horizontal)
    val tileRoadVertical = AppCompatResources.getDrawable(context, R.drawable.tile_road_vertical)
    val tileRoadCrossing4 = AppCompatResources.getDrawable(context, R.drawable.tile_road_crossing4)
    val tileRoadLeftBottom = AppCompatResources.getDrawable(context, R.drawable.tile_road_left_bottom)
    val tileRoadLeftTop= AppCompatResources.getDrawable(context, R.drawable.tile_road_left_top)
    val tileRoadRightBottom = AppCompatResources.getDrawable(context, R.drawable.tile_road_right_bottom)
    val tileRoadRightTop = AppCompatResources.getDrawable(context, R.drawable.tile_road_right_top)
    val tileGrass = AppCompatResources.getDrawable(context, R.drawable.tile_grass)
}

class Path(private var limitX: Int,private var limitY: Int, private var tileSize: Int){
    private var tiles = arrayListOf<Tile>()

    private fun isOccupied(x:Int,y:Int): Boolean{
        var boolean = false
        tiles.forEach {
            if(it.posX==x&&it.posY==y){
                boolean = true
                return@forEach
            }
        }
        return boolean
    }



    private fun getAvaibleFromJoint(joint: String,x:Int,y:Int){
        val roadHorizontal = RoadHorizontal(x,y,tileSize)
        val roadVertical = RoadVertical(x,y,tileSize)
        val roadRightBottom = RoadRightBottom(x,y,tileSize)
        val roadRightTop = RoadRightTop(x,y,tileSize)
        val roadLeftTop = RoadLeftTop(x,y,tileSize)
        val roadLeftBottom = RoadLeftBottom(x,y,tileSize)
        val roadCrossing4 = RoadCrossing4(x,y,tileSize)

        var avaible = arrayListOf<Tile>()
        when (joint) {
            "top" -> {
                avaible = arrayListOf(roadVertical,roadLeftBottom,roadRightBottom,roadCrossing4)

            }
            "bottom" -> {
                avaible = arrayListOf(roadVertical,roadLeftTop,roadRightTop,roadCrossing4)

            }
            "left" -> {
                avaible = arrayListOf(roadHorizontal,roadRightBottom,roadRightTop,roadCrossing4)

            }
            "right" -> {
                avaible = arrayListOf(roadHorizontal,roadLeftBottom,roadLeftTop,roadCrossing4)

            }
        }
    }


    fun createRandom(): ArrayList<Tile>{
        val tile = RoadVertical((0 until limitX).random(),0,tileSize)
        tile.jointTop="occupied"
        tiles.add(tile)

       while(true){
            var occupied=0
            for(i in 0 until tiles.size){

                val it = tiles[i]
                if(it.isFreeJoint()&&(!it.isHittingBorder(limitX,limitY))){
                    val x = it.getFreeJointX()
                    val y = it.getFreeJointY()

                    if(!isOccupied(x,y)){

                        val roadHorizontal = RoadHorizontal(x,y,tileSize)
                        val roadVertical = RoadVertical(x,y,tileSize)
                        val roadRightBottom = RoadRightBottom(x,y,tileSize)
                        val roadRightTop = RoadRightTop(x,y,tileSize)
                        val roadLeftTop = RoadLeftTop(x,y,tileSize)
                        val roadLeftBottom = RoadLeftBottom(x,y,tileSize)
                        val roadCrossing4 = RoadCrossing4(x,y,tileSize)
                        val tile2 : Tile

                        when {
                            it.freeJoint()=="top" -> {
                                tile2 = arrayListOf(roadVertical,roadLeftBottom,roadRightBottom,roadCrossing4).random()
                                tile2.jointBottom="occupied"
                                it.jointTop="occupied"
                                tiles.add(tile2)
                            }
                            it.freeJoint()=="bottom" -> {
                                tile2 = arrayListOf(roadVertical,roadLeftTop,roadRightTop,roadCrossing4).random()
                                tile2.jointTop="occupied"
                                it.jointBottom="occupied"
                                tiles.add(tile2)
                            }
                            it.freeJoint()=="left" -> {
                                tile2 = arrayListOf(roadHorizontal,roadRightBottom,roadRightTop,roadCrossing4).random()
                                tile2.jointRight="occupied"
                                it.jointLeft="occupied"
                                tiles.add(tile2)
                            }
                            it.freeJoint()=="right" -> {
                                tile2 = arrayListOf(roadHorizontal,roadLeftBottom,roadLeftTop,roadCrossing4).random()
                                tile2.jointLeft="occupied"
                                it.jointRight="occupied"
                                tiles.add(tile2)
                            }
                        }



                    }
                    else{
                        occupied++
                    }


                }


            }
           var j = 0
           tiles.forEach {
               if(it.isFreeJoint()) {
                   j++
                   return@forEach
               }
           }
           if(j<=0) break
           if(occupied==j) break
       }

        return tiles
    }

}

class Board(private var width : Int, private var height : Int, context: Context, extras: Bundle) {
    private var xTiles= extras.getInt("xTiles")
    private var yTiles = extras.getInt("yTiles")
    private var tileSize = 100
    private var tiles = arrayListOf<Tile>()
    private var drawables = Drawables(context)

    init{
        tiles = Path(xTiles,yTiles,tileSize).createRandom()
    }

    fun draw(canvas : Canvas){

        Terrain(0,0,xTiles*tileSize).draw(canvas,drawables)



        tiles.forEach{
            it.draw(canvas,drawables)
        }
    }


}