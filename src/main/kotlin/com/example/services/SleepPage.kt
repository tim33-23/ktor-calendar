package com.example.services

import com.example.controller.routing.Services.DateFormat
import com.example.dao.DAOFacade
import com.example.dao.DAOFacadeImpl
import com.example.dto.Child
import com.example.dto.Dream
import com.example.dto.DreamForTemplate
import com.example.dto.Parent
import com.example.model.ChildForTemplate
import com.example.model.SleepForTemplate
import io.ktor.util.reflect.*
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.*
import java.time.Period
import kotlin.math.abs

class SleepPage {

    val dao: DAOFacade = DAOFacadeImpl().apply {
        runBlocking {}
    }

    suspend fun getModelForSleepPagePreview(children: Child, count: Int): Map<Any, Any?>?{
        var nowDate = java.time.LocalDate.now()
        var now = java.time.LocalDateTime.now()
        var nowTime = DateFormat().format(LocalDateTime(now.year, now.month.value, now.dayOfMonth, now.hour, now.minute))
        if(count > 0){

            nowDate = nowDate.plusDays(count.toLong()-1)
        }
        else{
            nowDate = nowDate.minusDays(Math.abs(count.toLong()-1))
        }

        val years = nowDate.year
        val month = nowDate.month.value
        val day = nowDate.dayOfMonth
        val currentDate = DateFormat().format(LocalDate(years,month,day))
        val dreams = dao.dreams(children.idChild, LocalDate(years,month,day))
        var checkOnDream = false;
        if(dreams.isNotEmpty() && dreams.last().dateTimeSlEnded == null){
            checkOnDream = true;
        }

        var dreams2 = mutableListOf<DreamForTemplate>()
        for(dream in dreams){
            dreams2.add(DreamForTemplate(dream.idSleep, dream.idChild, DateFormat().format(dream.dateTimeSlStarted), DateFormat().format(dream.dateTimeSlEnded)))
        }
        return mapOf("model" to SleepForTemplate(currentDate, count-1, checkOnDream, dreams2, nowTime))
    }


    suspend fun getModelForSleepPage(children: Child, count: Int): Map<Any, Any?>?{
        var nowDate= java.time.LocalDate.now()
        var now = java.time.LocalDateTime.now()
        var nowTime = DateFormat().format(LocalDateTime(now.year, now.month.value, now.dayOfMonth, now.hour, now.minute))
        if(count >= 0){
            nowDate = nowDate.plusDays(count.toLong())
        }
        else{
            nowDate = nowDate.minusDays(Math.abs(count.toLong()))
        }

        val years = nowDate.year
        val month = nowDate.month.value
        val day = nowDate.dayOfMonth
        val currentDate = DateFormat().format(LocalDate(years,month,day))
        val dreams = dao.dreams(children.idChild, LocalDate(years,month,day))
        var checkOnDream = false;
        if(dreams.isNotEmpty() && dreams.last().dateTimeSlEnded == null){
            checkOnDream = true;
        }
        var dreams2 = mutableListOf<DreamForTemplate>()
        for(dream in dreams){
            dreams2.add(DreamForTemplate(dream.idSleep, dream.idChild, DateFormat().format(dream.dateTimeSlStarted), DateFormat().format(dream.dateTimeSlEnded)))
        }
        return mapOf("model" to SleepForTemplate(currentDate, count,checkOnDream, dreams2, nowTime))
    }

    suspend fun getModelForSleepPageNext(children: Child, count: Int): Map<Any, Any?>?{
        var nowDate= java.time.LocalDate.now()
        var now = java.time.LocalDateTime.now()
        var nowTime = DateFormat().format(LocalDateTime(now.year, now.month.value, now.dayOfMonth, now.hour, now.minute))
        if(count > -1){
            nowDate = nowDate.plusDays(count.toLong()+1)
        }
        else{
            nowDate = nowDate.minusDays(Math.abs(count.toLong()+1))
        }

        val years = nowDate.year
        val month = nowDate.month.value
        val day = nowDate.dayOfMonth
        val currentDate = DateFormat().format(LocalDate(years,month,day))
        val dreams = dao.dreams(children.idChild, LocalDate(years,month,day))
        var checkOnDream = false;
        if(dreams.isNotEmpty() && dreams.last().dateTimeSlEnded == null){
            checkOnDream = true;
        }
        var dreams2 = mutableListOf<DreamForTemplate>()
        for(dream in dreams){
            dreams2.add(DreamForTemplate(dream.idSleep, dream.idChild, DateFormat().format(dream.dateTimeSlStarted), DateFormat().format(dream.dateTimeSlEnded)))
        }
        return mapOf("model" to SleepForTemplate(currentDate, count+1, checkOnDream, dreams2, nowTime))
    }

}