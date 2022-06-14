package com.example.services



class ElectionsService{
    fun getElectionsForRegistration(): List<String>{
        return listOf<String>("Выборы главы администрации Липецкой области 08 сентября 2019 г.\t\t\t\t\t\n", "Выборы главы администрации Липецкой области 08 сентября 2020 г.\t\t\t\t\t\n")
    }

    fun getRoleForElection(): List<String>{
        return listOf<String>("Кандидат", "СМИ")
    }

    data class Event(val section: String,
                     val description: String,
                     val dataAndTimeBegin: String,
                     val dataAndTimeEnd: String,
                     val laws: List<Law>,
                     val roles: List<String>)

    data class Law(val article: String, val law: String)

    fun getEvents(election: String): List<Event>{
        return listOf<Event>(
            Event("1.ИЗБИРАТЕЛЬНЫЕ УЧАСТКИ",
            "Образование избирательных участков в местах временного пребывания избирателей",
                "11 июня 2019",
                "08 августа 2019",
                listOf(Law("ст 19 п 5", "ФЗ"), Law("ст 17 ч 3", "ОЗ")),
                listOf("Кандидат")
            ),
            Event("1.ИЗБИРАТЕЛЬНЫЕ УЧАСТКИ",
                "Публикация списков избирательных участков с указанием их границ либо перечня населенных пунктов, номеров, мест нахождения участковых избирательных комиссий и помещений для голосования",
                "11 июня 2019",
                "24 июля 2019",
                listOf(Law("ст.19 п.7 ФЗ", "ФЗ"), Law("ст.17 ч.6 ОЗ", "ОЗ")),
                listOf("Кандидат", "СМИ")
            ),
            Event("2. ФОРМИРОВАНИЕ УЧАСТКОВЫХ ИЗБИРАТЕЛЬНЫХ КОМИССИЙ. ДОПОЛНИТЕЛЬНОЕ ЗАЧИСЛЕНИЕ В РЕЗЕРВ СОСТАВОВ УЧАСТКОВЫХ ИЗБИРАТЕЛЬНЫХ КОМИССИЙ",
                "Публикация списков избирательных участков с указанием их границ либо перечня населенных пунктов, номеров, мест нахождения участковых избирательных комиссий и помещений для голосования",
                "11 июня 2019",
                "24 июля 2019",
                listOf(Law("ст.19 п.7 ФЗ", "ФЗ"), Law("ст.17 ч.6 ОЗ", "ОЗ")),
                listOf("СМИ")
            )
        )
    }
}