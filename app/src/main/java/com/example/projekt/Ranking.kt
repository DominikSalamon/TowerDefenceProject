package com.example.projekt

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.google.gson.Gson
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class Ranking(private var context: Context, private val fileName: String = "ranking.json"){

    class Record(
        var date: Long,
        var score: Int
    )
    class RecordList{
        var list = ArrayList<Record>()
    }

    private var list = ArrayList<Record>()

    fun draw(canvas: Canvas, marginLeft: Float, marginTop: Float){
        val paint = Paint()
        paint.flags = Paint.ANTI_ALIAS_FLAG
        paint.color = Color.BLACK
        paint.textSize = 30f

        canvas.drawText("Score: ", marginLeft, marginTop-50, paint)
        canvas.drawText("Date: ", marginLeft+200, marginTop-50, paint)

        for(i in 0 until 20){
            if(i >= list.size) break

            val sdf = SimpleDateFormat("dd/MM/yy hh:mm a")
            val netDate = Date(list[i].date)
            val date = sdf.format(netDate)

            canvas.drawText(list[i].score.toString(), marginLeft, marginTop+i*50, paint)
            canvas.drawText(date, marginLeft+200, marginTop+i*50, paint)
        }
    }

    fun sort(){
        for(i in list.indices){
            for(j in list.indices){
                if(list[i].score>list[j].score){
                    val temp = list[i]
                    list[i] = list[j]
                    list[j] = temp
                }
            }
        }
    }

    fun addRecord(date: Long, score: Int){
        list.add(Record(date,score))
    }

    fun toJSON(){
        val path = context.filesDir
        val file = File(path, fileName)

        val recordList = RecordList()
        recordList.list = list

        file.writeText(Gson().toJson(recordList),Charsets.UTF_8)
    }

    fun readJSON(){
        val path = context.filesDir
        val file = File(path, fileName)

        if(file.exists()){

            val recordList = Gson().fromJson(file.readText(), RecordList::class.java)

            if(recordList!=null){
                list = recordList.list
            }
        }
    }
}

