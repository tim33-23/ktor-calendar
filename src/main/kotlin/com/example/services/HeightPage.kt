package com.example.services

import com.example.controller.routing.Services.DateFormat
import com.example.dao.DAOFacade
import com.example.dao.DAOFacadeImpl
import com.example.dto.Child
import com.example.dto.ParametersBody
import com.example.dto.ParametersBodyForTemplates
import com.example.model.HeightForTemplate
import com.example.model.HeightString
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.*
import java.time.Period
import kotlin.math.exp


class HeightPage {

    val dao: DAOFacade = DAOFacadeImpl().apply {
        runBlocking {}
    }

    suspend fun getModelForHeightPage(idChild: Int): Map<Any, Any?>?{
        val child = dao.child(idChild)
        return if (child != null){
                    val nowDate = java.time.LocalDate.now()
                    val birthDate = child.dateOfBirth
                    val period = Period.between(birthDate.toJavaLocalDate(), nowDate)
                    val parametersBody = dao.allParametersBody(idChild)
                    val parametersBodyForTemplate = getParametersBodyForTemplate(parametersBody)
            if (parametersBody != null) {
                if(parametersBody.isNotEmpty()){
                    val value = parametersBody?.let { getFuctValue(it, child) }
                    val height = value?.let { HeightForTemplate(parametersBodyForTemplate, it.value, it.valueTeor) }
                    return mapOf("height" to height)
                }

            }
            return null

        } else{
            null
        }
    }

    fun getParametersBodyForTemplate(parametersBody: List<ParametersBody>?) : List<ParametersBodyForTemplates>{
        val parametersBodyForTemplates = mutableListOf<ParametersBodyForTemplates>()
        if (parametersBody != null) {
            for(body in parametersBody){
                val years = body.dateofAffixingCh.year
                val month = body.dateofAffixingCh.month.value
                val day = body.dateofAffixingCh.dayOfMonth
                val currentDate = DateFormat().format(LocalDate(years, month, day))

                parametersBodyForTemplates.add(ParametersBodyForTemplates(body.idBody, body.idChild, body.childHeightFact, body.childWeightFact, currentDate))
            }
        }
        return parametersBodyForTemplates
    }

    fun getFuctValue(parametersBody: List<ParametersBody>, child: Child) : HeightString{
        val par = parametersBody.last()
        val d1 = child.dateOfBirth

        val month = FloatArray(24)
        for(i in 0..23)
            month[i] = -1F
        var count = 0;
        var value = "["
        var last : Int = 0
        for (body in parametersBody){
            val d2 = body.dateofAffixingCh
            val period : DateTimePeriod = d1.periodUntil(d2)
            val m = period.months
            if(body.childHeightFact!=null){
                if(month[m] == -1F){
                    count++
                }
                month[m] = body.childHeightFact

            }

            last = m
        }
        var valueTeor = "["
        for(i in 0..last-1){
            if(month[i]==-1F){
                if(i==0){
                    value = value+"[]"
                    valueTeor = valueTeor + "[]"
                }
                else{
                    value = value+",[]"
                    valueTeor = valueTeor + ", []"
                }
            }
            else{
                if(i==0){
                    value = value+month[i].toString()
                    valueTeor = valueTeor + "[]"
                }
                else{
                    value = value+","+month[i].toString()
                    valueTeor = valueTeor + ",[]"
                }
            }
        }
        var vpred = month[last]
        for(i in last..11)
        {
            val v = (vpred + (1.2F * exp(i/23F)))
            valueTeor = valueTeor + "," + String.format("%.1f", v)
            vpred = v;
        }


        value = value + "]"
        valueTeor = valueTeor + "]"
        if(count<3){
            valueTeor = "[]"
        }
        if(last<1)
            value = "[]"
        return HeightString(value, valueTeor)
    }

}