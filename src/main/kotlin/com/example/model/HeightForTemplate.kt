package com.example.model

import com.example.dto.ParametersBody
import com.example.dto.ParametersBodyForTemplates
import java.time.Period


data class HeightForTemplate(val parametersBody: List<ParametersBodyForTemplates> , val value: String, val valueTeor: String)

data class HeightString(val value: String, val valueTeor: String)

