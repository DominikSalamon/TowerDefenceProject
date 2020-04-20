package com.example.projekt

import android.graphics.drawable.Drawable

class AnimatedDrawable(arr: Array<Drawable?>){
    private var array = ArrayList<Drawable>()
    private var currentIndex = 0

    init{
        arr.forEach{
            if (it != null) {
                array.add(it)
            }
        }


    }

    fun getDrawable(): Drawable{
        return array[currentIndex]
    }

    fun update(){
        currentIndex++
        if(currentIndex>=array.size)
            currentIndex=0
    }
}

