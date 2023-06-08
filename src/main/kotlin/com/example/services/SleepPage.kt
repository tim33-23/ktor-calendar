package com.example.services

import com.example.controller.routing.Services.DateFormat
import com.example.dao.DAOFacade
import com.example.dao.DAOFacadeImpl
import com.example.dto.Child
import com.example.dto.Dream
import com.example.dto.Parent
import com.example.model.ChildForTemplate
import com.example.model.SleepForTemplate
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toLocalDate
import java.time.Period

class SleepPage {

    val dao: DAOFacade = DAOFacadeImpl().apply {
        runBlocking {}
    }

    suspend fun getModelForSleepPagePreview(children: Child, count: Int): Map<Any, Any?>?{
        var nowDate= java.time.LocalDate.now()
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
        val dreams = dao.dreams(children.idChild, currentDate.toLocalDate())
        var checkOnDream = false;
        if(dreams.last().dateTimeSlEnded == null){
            checkOnDream = true;
        }
        return mapOf("model" to SleepForTemplate(currentDate, count-1, checkOnDream, dreams))
    }

    suspend fun getModelForSleepPage(children: Child, count: Int): Map<Any, Any?>?{
        var nowDate= java.time.LocalDate.now()
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
        val dreams = dao.dreams(children.idChild, currentDate.toLocalDate())
        var checkOnDream = false;
        if(dreams.last().dateTimeSlEnded == null){
            checkOnDream = true;
        }
        return mapOf("model" to SleepForTemplate(currentDate, count,checkOnDream, dreams))
    }

    suspend fun getModelForSleepPageNext(children: Child, count: Int): Map<Any, Any?>?{
        var nowDate= java.time.LocalDate.now()
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
        val dreams = dao.dreams(children.idChild, currentDate.toLocalDate())
        var checkOnDream = false;
        if(dreams.last().dateTimeSlEnded == null){
            checkOnDream = true;
        }
        return mapOf("model" to SleepForTemplate(currentDate, count+1, checkOnDream, dreams))
    }

}