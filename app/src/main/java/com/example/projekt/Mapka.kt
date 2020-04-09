package com.example.projekt

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


class Mapka(idMap: String,resources: Resources){
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

    private fun getDrawable(i: Int, drawables: Drawables): Drawable? {
        return when(i){
            0 -> drawables.tileGrass
            else -> drawables.tileRoad
        }
    }

    fun draw(canvas: Canvas, drawables: Drawables) {
        for (i in 0 until 11) {
            for (j in 0 until 15) {
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
}


data class Map(
    var width: Int,
    var height: Int,
    var tiles: Array<IntArray>
)