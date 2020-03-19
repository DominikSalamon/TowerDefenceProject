package com.example.projekt


class Path(private var limitX: Int,private var limitY: Int, private var tileSize: Int){
    private var tiles = arrayListOf<Tile>()

    private var roadVerticalLimit = 999
    private var roadHorizontalLimit = 999
    private var roadCrossing4Limit = 2
    private var roadRightTopLimit = 5
    private var roadRightBottomLimit = 5
    private var roadLeftBottomLimit = 5
    private var roadLeftTopLimit = 5

    private var roadVerticalProbability = 5
    private var roadHorizontalProbability = 5
    private var roadCrossing4Probability = 1
    private var roadRightTopProbability = 1
    private var roadRightBottomProbability = 1
    private var roadLeftBottomProbability = 1
    private var roadLeftTopProbability = 1


    private fun getTileFromString(tile: String,x: Int,y: Int): Tile{
        return when(tile){
            "roadHorizontal" -> RoadHorizontal(x,y,tileSize)
            "roadVertical" -> RoadVertical(x,y,tileSize)
            "roadCrossing4" -> RoadCrossing4(x,y,tileSize)
            "roadLeftTop" -> RoadLeftTop(x,y,tileSize)
            "roadLeftBottom" -> RoadLeftBottom(x,y,tileSize)
            "roadRightTop" -> RoadRightTop(x,y,tileSize)
            "roadRightBottom" -> RoadRightBottom(x,y,tileSize)
            else -> Terrain(x,y,tileSize)
        }
    }

    private fun getTilesLeft(tile: String): ArrayList<String>{

        val left = arrayListOf<String>()

        val probability = when(tile){
            "roadHorizontal" -> roadHorizontalProbability
            "roadVertical" -> roadVerticalProbability
            "roadCrossing4"-> roadCrossing4Probability
            "roadLeftTop" -> roadLeftTopProbability
            "roadLeftBottom" -> roadLeftBottomProbability
            "roadRightTop" -> roadRightTopProbability
            "roadRightBottom" -> roadRightBottomProbability
            else -> 0
        }
        if(getTileLimit(tile)>0){
            for(i in 0 until probability){
                left.add(tile)
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

    private fun getTileLimit(tileS: String): Int{
        return when(tileS){
            "roadHorizontal" -> roadHorizontalLimit
            "roadVertical" -> roadVerticalLimit
            "roadCrossing4"-> roadCrossing4Limit
            "roadLeftTop" -> roadLeftTopLimit
            "roadLeftBottom" -> roadLeftBottomLimit
            "roadRightTop" -> roadRightTopLimit
            "roadRightBottom" -> roadRightBottomLimit
            else -> 0
        }
    }

    private fun tileLimitDecrease(tileS: String){
        when(tileS){
            "roadHorizontal" -> roadHorizontalLimit--
            "roadVertical" -> roadVerticalLimit--
            "roadCrossing4"-> roadCrossing4Limit--
            "roadLeftTop" -> roadLeftTopLimit--
            "roadLeftBottom" -> roadLeftBottomLimit--
            "roadRightTop" -> roadRightTopLimit--
            "roadRightBottom" -> roadRightBottomLimit--
        }
    }

    private fun getAvaibleFromJoint(joint: String): ArrayList<String>{


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

        val tiles3 = arrayListOf<String>()

        avaible.forEach {
            getTilesLeft(it).forEach{it2 ->
                tiles3.add(it2)
            }
        }

        return tiles3
    }


    fun createRandom(): ArrayList<Tile>{
        val tile = RoadVertical(limitX/2,0,tileSize)
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

                        val tilesAvaible = getAvaibleFromJoint(freeJoint)

                        if(tilesAvaible.isNotEmpty()){
                            val tileS = tilesAvaible.random()
                            tileLimitDecrease(tileS)
                            val tile2 = getTileFromString(tileS,x,y)
                            when (freeJoint) {
                                "top" -> {
                                    tile2.jointBottom="occupied"
                                    it.jointTop="occupied"
                                }
                                "bottom" -> {
                                    tile2.jointTop="occupied"
                                    it.jointBottom="occupied"
                                }
                                "left" -> {
                                    tile2.jointRight="occupied"
                                    it.jointLeft="occupied"
                                }
                                "right" -> {
                                    tile2.jointLeft="occupied"
                                    it.jointRight="occupied"
                                }
                            }
                            tiles.add(tile2)
                        }
                        else{
                            when (freeJoint) {
                                "top" -> {
                                    it.jointTop="occupied"
                                }
                                "bottom" -> {
                                    it.jointBottom="occupied"
                                }
                                "left" -> {
                                    it.jointLeft="occupied"
                                }
                                "right" -> {
                                    it.jointRight="occupied"
                                }
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