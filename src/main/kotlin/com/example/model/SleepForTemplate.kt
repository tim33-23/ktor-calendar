package com.example.model

import com.example.dto.Dream

data class SleepForTemplate(val currentDate: String, val count: Int, val checkOnDreams: Boolean, val dreams: List<Dream>)