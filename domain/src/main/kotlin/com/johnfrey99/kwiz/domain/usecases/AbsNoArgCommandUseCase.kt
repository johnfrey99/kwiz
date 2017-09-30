package com.johnfrey99.kwiz.domain.usecases

import com.johnfrey99.kwiz.domain.usecases.AbsDisposableUseCase
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.observers.DisposableCompletableObserver

/**
 * Abstraction for single unit of work whose purpose is to change the state.
 */
abstract class AbsNoArgCommandUseCase(
        private val workScheduler: Scheduler,
        private val observeScheduler: Scheduler
) : AbsDisposableUseCase() {
    abstract fun buildUseCaseCompletable(): Completable
    fun execute(doOnComplete: () -> Unit,
                doOnError: (e: Throwable) -> Unit = {}
    ) {
        addDisposable(buildUseCaseCompletable()
                .subscribeOn(workScheduler)
                .observeOn(observeScheduler)
                .subscribeWith(object : DisposableCompletableObserver() {
                    override fun onComplete() = doOnComplete()
                    override fun onError(e: Throwable) = doOnError(e)
                }))
    }
}