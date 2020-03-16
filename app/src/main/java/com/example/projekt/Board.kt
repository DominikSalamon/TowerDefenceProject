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

    private var roadVerticalLimit = 100
    private var roadHorizontalLimit = 100
    private var roadCrossing4Limit = 2
    private var roadRightTopLimit = 4
    private var roadRightBottomLimit = 4
    private var roadLeftBottomLimit = 4
    private var roadLeftTopLimit = 4

    private var roadVerticalProbability = 3
    private var roadHorizontalProbability = 3
    private var roadCrossing4Probability = 2
    private var roadRightTopProbability = 1
    private var roadRightBottomProbability = 1
    private var roadLeftBottomProbability = 1
    private var roadLeftTopProbability = 1

    private fun getTilesLeft(tile: String,x: Int,y: Int): ArrayList<Tile>{

        val roadHorizontal = RoadHorizontal(x,y,tileSize)
        val roadVertical = RoadVertical(x,y,tileSize)
        val roadRightBottom = RoadRightBottom(x,y,tileSize)
        val roadRightTop = RoadRightTop(x,y,tileSize)
        val roadLeftTop = RoadLeftTop(x,y,tileSize)
        val roadLeftBottom = RoadLeftBottom(x,y,tileSize)
        val roadCrossing4 = RoadCrossing4(x,y,tileSize)

        val left = arrayListOf<Tile>()

        when(tile){
            "roadHorizontal" -> {
                if(roadHorizontalLimit-->0) {
                    for(i in 0 until roadHorizontalProbability){
                        left.add(roadHorizontal)
                    }
                }
            }
            "roadVertical" -> {
                if(roadVerticalLimit-->0){
                    for(i in 0 until roadVerticalProbability){
                        left.add(roadVertical)
                    }
                }
            }
            "roadCrossing4"-> {
                if(roadCrossing4Limit-->0){
                    for(i in 0 until roadCrossing4Probability){
                        left.add(roadCrossing4)
                    }
                }
            }
            "roadLeftTop" -> {
                if(roadLeftTopLimit-->0){
                    for(i in 0 until roadLeftTopProbability){
                        left.add(roadLeftTop)
                    }
                }
            }
            "roadLeftBottom" -> {
                if(roadLeftBottomLimit-->0){
                    for(i in 0 until roadLeftBottomProbability){
                        left.add(roadLeftBottom)
                    }
                }
            }
            "roadRightTop" -> {
                if(roadRightTopLimit-->0){
                    for(i in 0 until roadRightTopProbability){
                        left.add(roadRightTop)
                    }
                }
            }
            "roadRightBottom" -> {
                if(roadRightBottomLimit-->0) {
                    for(i in 0 until roadRightBottomProbability){
                        left.add(roadRightBottom)
                    }
                }
            }
        }





        return left
    }

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



    private fun getAvaibleFromJoint(joint: String,x:Int,y:Int): ArrayList<Tile>{


        var avaible = arrayListOf<String>()
        when (joint) {
            "top" -> {
                avaible = arrayListOf("roadVertical","roadCrossing4","roadLeftBottom","roadRightBottom")
            }
            "bottom" -> {
                avaible = arrayListOf("roadVertical","roadCrossing4","roadLeftTop","roadRightTop")
            }
            "left" -> {
                avaible = arrayListOf("roadHorizontal","roadCrossing4","roadRightBottom","roadRightTop")
            }
            "right" -> {
                avaible = arrayListOf("roadHorizontal","roadCrossing4","roadLeftBottom","roadLeftTop")
            }
        }

        val tiles3 = arrayListOf<Tile>()

        avaible.forEach {
            getTilesLeft(it,x,y).forEach{it2 ->
             tiles3.add(it2)
            }
        }

        return tiles3
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
                    val freeJoint = it.freeJoints().random()
                    val x = it.getFreeJointX(freeJoint)
                    val y = it.getFreeJointY(freeJoint)

                    if(!isOccupied(x,y)){

                        val tile2 = getAvaibleFromJoint(freeJoint,x,y).random()

                        when (freeJoint) {
                            "top" -> {
                                tile2.jointBottom="occupied"
                                it.jointTop="occupied"
                                tiles.add(tile2)
                            }
                            "bottom" -> {
                                tile2.jointTop="occupied"
                                it.jointBottom="occupied"
                                tiles.add(tile2)
                            }
                            "left" -> {
                                tile2.jointRight="occupied"
                                it.jointLeft="occupied"
                                tiles.add(tile2)
                            }
                            "right" -> {
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