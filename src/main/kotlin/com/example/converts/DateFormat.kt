package com.example.converts

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import java.time.format.DateTimeFormatter

class DateFormat {
    val PATTERN = "dd-MM-yyyy HH:mm"

    fun format(date: LocalDateTime?): String {
        val formatter = DateTimeFormatter.ofPattern(PATTERN)
        return date?.toJavaLocalDateTime()?.format(formatter).toString()
    }

/*    fun formatList(date: List<LocalDateTime>): String {
        val formatter = DateTimeFormatter.ofPattern(PATTERN)
        return date.forEach {
            format(formatter)
        }
    }*/
}