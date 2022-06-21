package com.example.converts

import kotlinx.datetime.*
import java.time.format.DateTimeFormatter

class DateFormat {
    val PATTERN_DATA_TIME = "dd-MM-yyyy HH:mm"
    val PATTERN_DATA = "dd-MM-yyyy"

    fun format(date: LocalDateTime?): String {
        val formatter = DateTimeFormatter.ofPattern(PATTERN_DATA_TIME)
        return date?.toJavaLocalDateTime()?.format(formatter).toString()
    }

    fun format(date: LocalDate?): String {
        val formatter = DateTimeFormatter.ofPattern(PATTERN_DATA)
        return date?.toJavaLocalDate()?.format(formatter).toString()
    }

    fun format(date: LocalDate?, day: Int): String {
        val formatter = DateTimeFormatter.ofPattern(PATTERN_DATA)
        return date?.toJavaLocalDate()?.plusDays(day.toLong())?.format(formatter).toString()
    }

/*    fun formatList(date: List<LocalDateTime>): String {
        val formatter = DateTimeFormatter.ofPattern(PATTERN)
        return date.forEach {
            format(formatter)
        }
    }*/
}