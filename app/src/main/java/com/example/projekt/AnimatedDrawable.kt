package com.example.projekt

import android.graphics.drawable.Drawable

class AnimatedDrawable(arr: Array<Drawable?>){
    private var array = ArrayList<Drawable>()
    private var interval = 150 //ms
    private var currentIndex = 0
    private var lastDraw: Long = 0

    init{
        arr.forEach{
            if (it != null) {
                array.add(it)
            }
        }
    }

    fun getDrawable(): Drawable{

        if(System.currentTimeMillis()-lastDraw>interval){
            currentIndex++
            if(currentIndex>=array.size)
                currentIndex=0

            lastDraw = System.currentTimeMillis()
        }

        return array[currentIndex]
    }
}

