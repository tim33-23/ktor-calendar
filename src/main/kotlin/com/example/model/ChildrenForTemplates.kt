package com.example.model

import kotlinx.datetime.LocalDate
import java.time.Period


data class ChildForTemplate(val idChild: Int, val idParents: Int, val name: String, val dateOfBirth: String, val gender: Boolean, val period: Period)