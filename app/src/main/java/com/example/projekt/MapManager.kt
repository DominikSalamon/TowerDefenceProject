package com.example.projekt

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


class MapManager(idMap: String, resources: Resources, private val drawables: Drawables,private val tileSize: Int){
    private var map: Map

    init{
        val a = when(idMap){
            "1" -> R.raw.map1
            "2" -> R.raw.map2
            "3" -> R.raw.map3
            "4" -> R.raw.map4
            else -> R.raw.map1
        }

        val mapString = readRawTextFile(resources, a)
        map = Gson().fromJson(mapString, Map::class.java)
    }



    fun drawMap(canvas: Canvas) {
        for (i in 0 until map.height) {
            for (j in 0 until map.width) {
                val drawable = getDrawable(map.tiles[i][j], drawables)
                drawable?.setBounds(
                    j * tileSize,
                    i * tileSize,
                    (j + 1) * tileSize,
                    (i + 1) * tileSize
                )
                drawable?.draw(canvas)
            }
        }
    }

    private fun readRawTextFile(resources: Resources, resId: Int): String? {
        val inputStream: InputStream = resources.openRawResource(resId)
        val inputreader = InputStreamReader(inputStream)
        val buffreader = BufferedReader(inputreader)
        var line: String?
        val text = StringBuilder()
        try {
            while (buffreader.readLine().also { line = it } != null) {
                text.append(line)
                text.append('\n')
            }
        } catch (e: IOException) {
            return null
        }
        return text.toString()
    }

    private fun getDrawable(i: Int, drawables: Drawables): Drawable? {
        return when(i){
            0 -> drawables.tileGrass
            1 -> drawables.tileRoad
            2 -> drawables.tileRoad2
            3 -> drawables.turnRight1
            4 -> drawables.turnRight2
            5 -> drawables.turnLeft1
            6 -> drawables.turnLeft2
            7 -> drawables.trees
            8 -> drawables.mountain
            9 -> drawables.crossroadL
            10 -> drawables.crossroadR
            else -> null
        }
    }

    fun isTileFree(x: Int, y: Int): Boolean{
        return if(x>=0&&x<map.width&&y>=0&&y<map.height){
            map.tiles[y][x]==0
        } else
            false
    }

    fun getMapWidth(): Int{
        return tileSize*map.width
    }
    fun getMapHeight(): Int{
        return tileSize*map.height
    }
    fun getEnemyWayPoints(): Array<IntArray>{
        return map.enemyWayPoints
    }
}


class Map(
    var width: Int,
    var height: Int,
    var tiles: Array<IntArray>,
    var enemyWayPoints: Array<IntArray>
)