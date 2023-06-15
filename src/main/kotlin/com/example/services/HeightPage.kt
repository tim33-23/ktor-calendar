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
import kotlin.math.ln
import kotlin.math.pow


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
                    val value = parametersBody?.let { getFuctHeightValue(it, child) }
                    val height = value?.let { HeightForTemplate(parametersBodyForTemplate, it.value, it.valueTeor) }
                    return mapOf("height" to height)
                }
            }
            return null

        } else{
            null
        }
    }

    suspend fun getModelForWeightPage(idChild: Int): Map<Any, Any?>?{
        val child = dao.child(idChild)
        return if (child != null){
            val nowDate = java.time.LocalDate.now()
            val birthDate = child.dateOfBirth
            val period = Period.between(birthDate.toJavaLocalDate(), nowDate)
            val parametersBody = dao.allParametersBody(idChild)
            val parametersBodyForTemplate = getParametersBodyForTemplate(parametersBody)
            if (parametersBody != null) {
                if(parametersBody.isNotEmpty()){
                    val value = parametersBody?.let { getFuctWeightValue(it, child) }
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

    fun getFuctHeightValue(parametersBody: List<ParametersBody>, child: Child) : HeightString{
        val par = parametersBody.last()
        val d1 = child.dateOfBirth

        val month = FloatArray(24)
        for(i in 0..23)
            month[i] = -1F
        var count = 0
        var value = "["
        var last : Int = 0
        for (body in parametersBody){
            val d2 = body.dateofAffixingCh
            val period : DateTimePeriod = d1.periodUntil(d2)
            val m = period.months
            if(body.childHeightFact!=null){
                if(month[m] == -1F){
                    month[m] = body.childHeightFact
                    if(last<m)
                        last = m
                }

            }
        }
        var sumLnt = 0.0
        var sumLnY = 0.0
        var sumLnYXLnT = 0.0
        var sumlnTXLnT = 0.0
        var valueTeor = "["
        for(i in 0..last){
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
                count++
                sumLnY += ln(month[i]/10)
                sumLnt += ln((i+1).toDouble())
                sumLnYXLnT += ln(month[i]/10)*ln((i+1).toDouble())
                sumlnTXLnT += ln((i+1).toDouble())*ln((i+1).toDouble())
                if(i==0){
                    value = value+month[i].toString()
                    valueTeor = valueTeor + "[]"
                }
                else{
                    value = value+","+ month[i].toString()
                    valueTeor = valueTeor + ",[]"
                }
            }
        }
        val arg21 = sumLnt/count
        val arg22 = sumlnTXLnT/sumLnt
        val arg31 = sumLnY/count
        val arg32 = sumLnYXLnT/sumLnt
        val argPreB = arg21-arg22
        val b = (arg31-arg32)/(argPreB)
        val lna = arg31-(argPreB*b)
        val a = exp(lna)
        for(i in last+1..11)
        {
            val v = a*((i).toDouble().pow(b))*10-10
            valueTeor = valueTeor + "," + String.format("%.1f", v.toFloat()).replace(",", ".")
        }


        value = value + "]"
        valueTeor = valueTeor + "]"
        if(count<3){
            valueTeor = "[]"
        }
        if(count == 0)
            value = "[]"
        return HeightString(value, valueTeor)
    }

    fun getFuctWeightValue(parametersBody: List<ParametersBody>, child: Child) : HeightString{
        val par = parametersBody.last()
        val d1 = child.dateOfBirth

        val month = FloatArray(24)
        for(i in 0..23)
            month[i] = -1F
        var count = 0
        var value = "["
        var last : Int = 0
        for (body in parametersBody){
            val d2 = body.dateofAffixingCh
            val period : DateTimePeriod = d1.periodUntil(d2)
            val m = period.months
            if(body.childWeightFact!=null){
                if(month[m] == -1F){
                    month[m] = body.childWeightFact
                    if(last<m)
                        last = m
                }

            }
        }
        var sumLnt = 0.0
        var sumLnY = 0.0
        var sumLnYXLnT = 0.0
        var sumlnTXLnT = 0.0
        var valueTeor = "["
        for(i in 0..last){
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
                count++
                sumLnY += ln(month[i]/1000)
                sumLnt += ln((i+1).toDouble())
                sumLnYXLnT += ln(month[i]/1000)*ln((i+1).toDouble())
                sumlnTXLnT += ln((i+1).toDouble())*ln((i+1).toDouble())
                if(i==0){
                    value = value+month[i].toString()
                    valueTeor = valueTeor + "[]"
                }
                else{
                    value = value+","+ month[i].toString()
                    valueTeor = valueTeor + ",[]"
                }
            }
        }
        val arg21 = sumLnt/count
        val arg22 = sumlnTXLnT/sumLnt
        val arg31 = sumLnY/count
        val arg32 = sumLnYXLnT/sumLnt
        val argPreB = arg21-arg22
        val b = (arg31-arg32)/(argPreB)
        val lna = arg31-(argPreB*b)
        val a = exp(lna)
        for(i in last+1..11)
        {
            val v = a*((i).toDouble().pow(b))*1000-1000
            valueTeor = valueTeor + "," + String.format("%.1f", v.toFloat()).replace(",", ".")
        }


        value = value + "]"
        valueTeor = valueTeor + "]"
        if(count<3){
            valueTeor = "[]"
        }
        if(count == 0)
            value = "[]"
        return HeightString(value, valueTeor)
    }

}