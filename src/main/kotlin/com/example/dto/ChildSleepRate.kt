package com.example.dto


import org.jetbrains.exposed.sql.Table

import org.jetbrains.exposed.sql.kotlin.datetime.datetime
import java.sql.Time

data class ChildSleepRate(val idSlNorm: Int, val slDuration: Time, val numberSl: Int,val oldBaby: Int,val daytimeSl: Int)

object ChildSleepRates : Table() {
    val idSlNorm = integer("id_sleep_norms").autoIncrement()
    val slDuration = datetime("sleep_duration_norm")
    val numberSl = integer("number_of_sleep")
    val oldBaby = integer("month_old_baby")
    val daytimeSl = integer("daytime_sleep")



    override val primaryKey = PrimaryKey(idSlNorm)

}