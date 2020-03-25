package com.example.projekt

import android.content.Context
import androidx.appcompat.content.res.AppCompatResources

class Drawables(context: Context){
    val tileRoad = AppCompatResources.getDrawable(context, R.drawable.tile_road)
    val tileRoadHorizontal = AppCompatResources.getDrawable(context, R.drawable.tile_road_horizontal)
    val tileRoadVertical = AppCompatResources.getDrawable(context, R.drawable.tile_road_vertical)
    val tileRoadCrossing4 = AppCompatResources.getDrawable(context, R.drawable.tile_road_crossing4)
    val tileRoadLeftBottom = AppCompatResources.getDrawable(context, R.drawable.tile_road_left_bottom)
    val tileRoadLeftTop= AppCompatResources.getDrawable(context, R.drawable.tile_road_left_top)
    val tileRoadRightBottom = AppCompatResources.getDrawable(context, R.drawable.tile_road_right_bottom)
    val tileRoadRightTop = AppCompatResources.getDrawable(context, R.drawable.tile_road_right_top)
    val tileGrass = AppCompatResources.getDrawable(context, R.drawable.tile_grass)
    val tileRoadLeftTopRight = AppCompatResources.getDrawable(context, R.drawable.tile_road_left_top_right)
    val tileRoadTopRightBottom = AppCompatResources.getDrawable(context, R.drawable.tile_road_top_right_bottom)
    val tileRoadRightBottomLeft = AppCompatResources.getDrawable(context, R.drawable.tile_road_right_bottom_left)
    val tileRoadBottomLeftTop= AppCompatResources.getDrawable(context, R.drawable.tile_road_bottom_left_top)
}