package com.yaroslavzghoba.data.util

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

/**
 * Calculate initial delay for background work
 *
 * @param targetTime of work start
 * @return duration between now and closest [targetTime] in milliseconds
 */
internal fun getInitialDelay(targetTime: LocalTime): Long {
    val now = Clock.System.now()
    val timeZone = TimeZone.currentSystemDefault()
    val today = now.toLocalDateTime(timeZone).date
    val targetDateTime = LocalDateTime(date = today, time = targetTime).let {
        if (it.toInstant(timeZone) < now) {
            val tomorrow = it.date.plus(1, DateTimeUnit.DAY)
            LocalDateTime(date = tomorrow, time = it.time)
        } else it
    }
    return (targetDateTime.toInstant(timeZone) - now).inWholeMilliseconds
}