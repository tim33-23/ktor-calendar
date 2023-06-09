package com.example.model


import com.example.dto.DreamForTemplate
import java.time.LocalDateTime
import java.time.LocalTime

data class SleepForTemplate(val currentDate: String, val count: Int, val checkOnDreams: Boolean, val dreams: List<DreamForTemplate>, val now: String)