package com.example.projekt

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


class MapFactory(idMap: String,resources: Resources){
    private val tileSize = 100
    private var map: Map

    init{
        val a = when(idMap){
            "1" -> R.raw.map1
            "2" -> R.raw.map2
            "3" -> R.raw.map3
            else -> R.raw.map1
        }

        val mapString = readRawTextFile(resources, a)
        map = Gson().fromJson(mapString, Map::class.java)
    }

    fun drawMap(canvas: Canvas, drawables: Drawables) {
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
            else -> drawables.tileRoad
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
    fun getMapX(): Int{
        return map.width
    }
    fun getMapY(): Int{
        return map.height
    }
}


class Map(
    var width: Int,
    var height: Int,
    var tiles: Array<IntArray>
)