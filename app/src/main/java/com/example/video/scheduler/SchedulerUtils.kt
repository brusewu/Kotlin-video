package com.example.video.scheduler

/**
 * Created by bruse on 2019/7/1.
 */
object SchedulerUtils {

    fun <T> ioToMain(): IoMainScheduler<T> {
        return IoMainScheduler()
    }
}