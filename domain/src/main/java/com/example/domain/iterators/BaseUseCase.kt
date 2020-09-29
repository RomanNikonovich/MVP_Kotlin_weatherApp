package com.example.domain.iterators

import com.example.domain.executor.PostExecutionThread
import com.example.domain.executor.ThreadExecution
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class BaseUseCase(
    postExecutionThread: PostExecutionThread,
    threadExecution: ThreadExecution? = null
) {
    protected val postExecutionThreadScheduler = postExecutionThread.getScheduler()

    protected var threadExecutionScheduler = Schedulers.io()

    init {
        if (threadExecution != null)
            threadExecutionScheduler = Schedulers.from(threadExecution)
    }

}