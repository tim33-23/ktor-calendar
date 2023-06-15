package com.example.services

import com.example.controller.routing.Services.DateFormat
import com.example.dao.DAOFacade
import com.example.dao.DAOFacadeImpl
import com.example.dto.Child
import com.example.dto.Dream
import com.example.dto.DreamForTemplate
import com.example.model.DreamsWithStatistic

import com.example.model.SleepForTemplate
import com.example.model.StatisticSleeping
import io.ktor.util.reflect.*
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.*
import kotlinx.datetime.toJavaLocalDateTime
import java.sql.Time
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
        return mapOf("model" to SleepForTemplate(currentDate, count-1, checkOnDream, dreams2.dreams, nowTime, dreams2.statistic))
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
        return mapOf("model" to SleepForTemplate(currentDate, count,checkOnDream, dreams2.dreams, nowTime, dreams2.statistic))
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
        return mapOf("model" to SleepForTemplate(currentDate, count+1, checkOnDream, dreams2.dreams, nowTime, dreams2.statistic))
    }


    fun setDreamListForTemplate(dreams: List<Dream>, nowTime: String): DreamsWithStatistic{
        var dreams2 = mutableListOf<DreamForTemplate>()

        var countDream: Int = 0
        var timeDream : DateTimePeriod? = null
        var timeDay : DateTimePeriod? = null
        var timeNoSleep: DateTimePeriod? = null
        var timeNightSleep: DateTimePeriod?=null
        for(dream in dreams){
            countDream++
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
                    if(timeNoSleep==null){
                        timeNoSleep = period
                    }
                    else{
                        timeNoSleep += period
                    }
                    if(d3.date.compareTo(d2.date) == 1){
                        day=true

                    }



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
                if(timeDream==null){
                    timeDream = period
                }
                else{
                    timeDream += period
                }
                var day: Boolean = false
                if(d2.date.compareTo(d1.date) == 0){
                    day=true
                    if(timeDay==null){
                        timeDay = period
                    }
                    else{
                        timeDay += period
                    }
                }
                else{
                    if(timeNightSleep==null){
                        timeNightSleep = period
                    }
                    else{
                        timeNightSleep += period
                    }
                }

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
                if(timeDream==null){
                    timeDream = period
                }
                else{
                    timeDream += period
                }
                var day: Boolean = false
                if(d1.date.compareTo(d2.date) == 0){
                    day=true
                    if(timeDay==null){
                        timeDay = period
                    }
                    else{
                        timeDay += period
                    }
                }
                else{
                    if(timeNoSleep==null){
                        timeNoSleep = period
                    }
                    else{
                        timeNoSleep += period
                    }

                }

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
            if(timeNoSleep==null){
                timeNoSleep = period
            }
            else{
                timeNoSleep += period
            }
            if(d1.date.compareTo(d2.date) == 0)
            {
                day=true
            }


            dreams2.add(DreamForTemplate(-1, -1, DateFormat().format(d1), "0", periodString, day))

        }
        var allTimeDream = "-"
        var timeDayDream = "-"
        var timeNoSleeping = "-"
        var timeNight = "-"
        if(timeDay!=null){
            timeDayDream = timeDay.hours.toString()+ " часов "+timeDay.minutes.toString()+" минут"
        }
        var hours = 0
        var minute = 0
        if(timeDream!=null){
            if(timeDay!=null){
                if(timeDream.minutes<timeDay.minutes){
                    hours = timeDream.hours - 1 - timeDay.hours
                    minute = timeDream.minutes + 60 - timeDay.minutes
                }else{
                    hours = timeDream.hours - timeDay.hours
                    minute = timeDream.minutes - timeDay.minutes
                }
                timeNight = hours.toString() + " часов "+minute.toString()+" минут"
            }
            else{
                timeNight = timeDream.hours.toString()+ " часов "+timeDream.minutes.toString()+" минут"
            }

            allTimeDream = timeDream.hours.toString()+ " часов "+timeDream.minutes.toString()+" минут"
        }

        if(timeNoSleep!=null){
            timeNoSleeping = timeNoSleep.hours.toString()+ " часов "+timeNoSleep.minutes.toString()+" минут"
        }


        return DreamsWithStatistic(dreams2, StatisticSleeping(allTimeDream, timeDayDream, timeNoSleeping, countDream, timeNight))
    }

}