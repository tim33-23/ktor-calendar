package com.example.services

import com.example.controller.routing.Services.DateFormat
import com.example.dao.DAOFacade
import com.example.dao.DAOFacadeImpl
import com.example.dto.Child
import com.example.dto.Dream
import com.example.dto.DreamForTemplate

import com.example.model.SleepForTemplate
import io.ktor.util.reflect.*
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.*
import kotlinx.datetime.toJavaLocalDateTime
import java.time.format.DateTimeFormatter

class SleepPage {

    val dao: DAOFacade = DAOFacadeImpl().apply {
        runBlocking {}
    }

    suspend fun getModelForSleepPagePreview(children: Child, count: Int): Map<Any, Any?>?{
        var nowDate = java.time.LocalDate.now()
        val now = java.time.LocalDateTime.now()
        val nowTime = DateFormat().format(LocalDateTime(now.year, now.month.value, now.dayOfMonth, now.hour, now.minute))
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

        val dreams2 = setDreamListForTemplate(dreams, nowTime)
        return mapOf("model" to SleepForTemplate(currentDate, count-1, checkOnDream, dreams2, nowTime))
    }


    suspend fun getModelForSleepPage(children: Child, count: Int): Map<Any, Any?>?{
        var nowDate = java.time.LocalDate.now()
        val now = java.time.LocalDateTime.now()
        val nowTime = DateFormat().format(LocalDateTime(now.year, now.month.value, now.dayOfMonth, now.hour, now.minute))
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
        val dreams2 = setDreamListForTemplate(dreams, nowTime)
        return mapOf("model" to SleepForTemplate(currentDate, count,checkOnDream, dreams2, nowTime))
    }

    suspend fun getModelForSleepPageNext(children: Child, count: Int): Map<Any, Any?>?{
        var nowDate= java.time.LocalDate.now()
        val now = java.time.LocalDateTime.now()
        val nowTime = DateFormat().format(LocalDateTime(now.year, now.month.value, now.dayOfMonth, now.hour, now.minute))
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
        val dreams2 = setDreamListForTemplate(dreams, nowTime)
        return mapOf("model" to SleepForTemplate(currentDate, count+1, checkOnDream, dreams2, nowTime))
    }


    fun setDreamListForTemplate(dreams: List<Dream>, nowTime: String): List<DreamForTemplate>{
        var dreams2 = mutableListOf<DreamForTemplate>()
        for(dream in dreams){
            if(dream.dateTimeSlEnded!=null){
                if(dreams2.isNotEmpty() && dreams2.last().dateTimeSlEnded!="0"){
                    val pattern = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
                    val d1 = java.time.LocalDateTime.parse(dreams2.last().dateTimeSlEnded, pattern)
                    val d3 = d1.toKotlinLocalDateTime()
                    val d2 = dream.dateTimeSlStarted
                    val period = d2.let { d3.toInstant(TimeZone.UTC).periodUntil(it.toInstant(TimeZone.UTC), TimeZone.UTC) }
                    var periodString:String
                    if(period.hours!=0){
                        periodString = period.hours.toString()+" часов "+period.minutes.toString()+" минут"
                    }
                    else{
                        periodString = period.minutes.toString() + " минут"
                    }
                    var day: Boolean = false
                    if(d3.date.compareTo(d2.date) == 1)
                        day=true
                        dreams2.add(DreamForTemplate(-1, -1, DateFormat().format(d3), DateFormat().format(d2), periodString, day))
                }
                val d1 = dream.dateTimeSlStarted
                val d2 = dream.dateTimeSlEnded
                val period: DateTimePeriod = d1.toInstant(TimeZone.UTC).periodUntil(d2.toInstant(TimeZone.UTC), TimeZone.UTC)
                var periodString:String
                if(period.hours!=0){
                    periodString = period.hours.toString()+" часов "+period.minutes.toString()+" минут"
                }
                else{
                    periodString = period.minutes.toString() + " минут"
                }
                var day: Boolean = false
                if(d2.date.compareTo(d1.date) == 0)
                    day=true
                dreams2.add(DreamForTemplate(dream.idSleep, dream.idChild, DateFormat().format(dream.dateTimeSlStarted), DateFormat().format(dream.dateTimeSlEnded), periodString, day))
            }
            else{
                val d1 = dream.dateTimeSlStarted
                val pattern = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
                val d2 = java.time.LocalDateTime.parse(nowTime, pattern).toKotlinLocalDateTime()
                val period: DateTimePeriod = d1.toInstant(TimeZone.UTC).periodUntil(d2.toInstant(TimeZone.UTC), TimeZone.UTC)
                var periodString:String
                if(period.hours!=0){
                    periodString = period.hours.toString()+" часов "+period.minutes.toString()+" минут"
                }
                else{
                    periodString = period.minutes.toString() + " минут"
                }
                var day: Boolean = false
                if(d1.date.compareTo(d2.date) == 0)
                    day=true
                dreams2.add(DreamForTemplate(dream.idSleep, dream.idChild, DateFormat().format(d1), "0", periodString, day))
            }
        }
        if(dreams2.isNotEmpty() && dreams2.last().dateTimeSlEnded!=nowTime && dreams2.last().dateTimeSlEnded!="0"){
            val pattern = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
            val d1 = java.time.LocalDateTime.parse(dreams2.last().dateTimeSlEnded, pattern).toKotlinLocalDateTime()
            val d2 = java.time.LocalDateTime.parse(nowTime, pattern).toKotlinLocalDateTime()
            val period: DateTimePeriod = d1.toInstant(TimeZone.UTC).periodUntil(d2.toInstant(TimeZone.UTC), TimeZone.UTC)
            var periodString:String
            if(period.hours!=0){
                periodString = period.hours.toString()+ " часов "+period.minutes.toString()+" минут"
            }
            else{
                periodString = period.minutes.toString() + " минут"
            }
            var day: Boolean = false
            if(d1.date.compareTo(d2.date) == 0)
                day=true
            dreams2.add(DreamForTemplate(-1, -1, DateFormat().format(d1), "0", periodString, day))

        }



        return dreams2
    }

}