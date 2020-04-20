package com.example.projekt

class Ticker {
    private var lastUpdate = System.currentTimeMillis()
    private var tickList = ArrayList<Tick>()


    fun newTick(i: Int): Tick {
        val tick = Tick(i)

        tickList.add(tick)

        return tick
    }

    fun update(){
        lastUpdate = System.currentTimeMillis()
        tickList.forEach{
            it.update(lastUpdate)
        }
    }
}

class Tick(private val interval: Int){
    private var lastUpdate = System.currentTimeMillis()
    private var tick = false

    fun get(): Boolean{
        val toReturn = tick
        tick = false
        return toReturn
    }

    fun update(lastUpdate: Long) {
        if(lastUpdate-this.lastUpdate>=interval){
            tick=true
            this.lastUpdate = lastUpdate
        }
    }
}