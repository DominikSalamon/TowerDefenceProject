package com.example.projekt

import android.graphics.Canvas

class TowerManager {
    private val towerList = ArrayList<Tower>()
    private lateinit var enemyManager: EnemyManager
    private lateinit var attacksManager: AttacksManager

    @Synchronized fun updateTowers() {
        for(tower in towerList)
            tower.update(enemyManager.getEnemyList(),attacksManager)
    }

    fun setAttacksManager(manager: AttacksManager){
        attacksManager = manager
    }

    fun setEnemyManager(manager: EnemyManager){
        enemyManager = manager
    }

    fun drawTowers(canvas: Canvas) {
        for(i in 0 until towerList.size){
             if(i<towerList.size)   towerList[i].draw(canvas)
        }
    }

    fun isPlaceFree(X: Int, Y:Int): Boolean{
        towerList.forEach{
            if(it.collides(X,Y)){
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

    fun removeTower(tower: Tower) {
        towerList.remove(tower)
    }

    fun findTower(X: Int, Y:Int): Tower?{
        var tower: Tower? = null
        towerList.forEach{
            if(it.collides(X,Y)){
                tower = it
                return tower
            }
        }
        return tower
    }
}