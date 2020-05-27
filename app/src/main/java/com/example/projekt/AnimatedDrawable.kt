package com.example.projekt

import android.graphics.drawable.Drawable

class AnimatedDrawable(arr: Array<Drawable?>){
    private var ticker = Ticker()
    private var nextFrame = ticker.newTick(700)
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
        ticker.update()
        if(nextFrame.get()){
            currentIndex++
            if(currentIndex>=array.size)
                currentIndex=0
        }
    }
}

