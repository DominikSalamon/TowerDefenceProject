package com.example.projekt

import android.graphics.Canvas

class TowerManager {
    private val towerList = ArrayList<Tower>()
    private lateinit var enemyManager: EnemyManager
    private lateinit var attacksManager: AttacksManager
    private lateinit var ticker: Ticker

    fun updateTowers() {
        for(tower in towerList){

            tower.update(enemyManager.getEnemyList(),attacksManager)

        }

    }

    fun setAttacksManager(manager: AttacksManager){
        attacksManager = manager
    }

    fun setEnemyManager(manager: EnemyManager){
        enemyManager = manager
    }

    fun setTicker(ticker: Ticker){
        this.ticker = ticker
    }

    fun drawTowers(canvas: Canvas) {
        for(i in 0 until towerList.size){
            towerList[i].draw(canvas)
        }
    }

    fun isPlaceFree(X: Int, Y:Int): Boolean{
        towerList.forEach{
            if(it.colides(X,Y)){
                return false
            }
        }

        return true
    }

    fun addTowers(boughtTowers: ArrayList<Tower>) {
        boughtTowers.forEach{
            towerList.add(it)
        }
    }
}