package com.example.projekt

import android.graphics.Canvas

class AttacksManager{
    private val attackList = ArrayList<Attack>()

    fun draw(canvas: Canvas){
        attackList.forEach{
            it.draw(canvas)
        }
    }

    fun updateAttacks(){
        val toDelete = arrayListOf<Attack>()

        for(attack in attackList){
           attack.update()

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