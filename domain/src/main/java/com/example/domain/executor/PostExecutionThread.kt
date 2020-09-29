package com.example.domain.executor

import io.reactivex.rxjava3.core.Scheduler

interface PostExecutionThread {
    fun getScheduler() : Scheduler
}