package com.example.model


import com.example.dto.DreamForTemplate
import java.time.LocalDateTime
import java.time.LocalTime

data class SleepForTemplate(val currentDate: String, val count: Int, val checkOnDreams: Boolean, val dreams: List<DreamForTemplate>, val now: String, val statistic: StatisticSleeping)

data class StatisticSleeping(val allTimeDream:String, val timeDayDream: String, val timeNoSleeping: String, val countDream: Int, val timeNightDream: String)

data class DreamsWithStatistic(val dreams: List<DreamForTemplate>, val statistic: StatisticSleeping)