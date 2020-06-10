package com.example.projekt

import android.graphics.Canvas
import java.lang.Math.pow
import kotlin.math.pow
import kotlin.math.sqrt


class AttacksManager{
    private val attackList = ArrayList<Attack>()
    private lateinit var enemyManager: EnemyManager
    fun draw(canvas: Canvas){
        attackList.forEach{
            it.draw(canvas)
        }
    }

    fun setEnemyManager(eM: EnemyManager){
        enemyManager= eM
    }

    private fun distance(attack: Lava, enemy: Enemy): Float {
        return  sqrt((attack.posX.toFloat() - enemy.getX()).pow(2)+(attack.posY.toFloat() - enemy.getY()).pow(2))
    }

    fun updateAttacks(){
        val toDelete = arrayListOf<Attack>()

        for(attack in attackList){
           attack.update()

            if(attack is Lava){
                enemyManager.getEnemyList().forEach {
                    if(distance(attack,it)<100){
                        it.health-=attack.damage
                        toDelete.add(attack)
                    }
                }
            }

            if(!attack.onGoing()){
                toDelete.add(attack)
            }


        }


        attackList.removeAll(toDelete)


    }

    fun addAttack(attack: Attack) {
        attackList.add(attack)
    }


}

