package com.johnfrey99.kwiz.domain.usecases

import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.observers.DisposableCompletableObserver

/**
 * Abstraction for single unit of work whose purpose is to change the state.
 *
 * @param <Params> Parameters needed to perform the unit of work.
 */
abstract class AbsCommandUseCase<in Params>(
        private val workScheduler: Scheduler,
        private val observeScheduler: Scheduler
) : AbsDisposableUseCase() {
    abstract fun buildUseCaseCompletable(params: Params): Completable
    fun execute(params: Params,
                doOnComplete: () -> Unit,
                doOnError: (e: Throwable) -> Unit = {}
    ) {
        addDisposable(buildUseCaseCompletable(params)
                .subscribeOn(workScheduler)
                .observeOn(observeScheduler)
                .subscribeWith(object : DisposableCompletableObserver() {
                    override fun onComplete() = doOnComplete()
                    override fun onError(e: Throwable) = doOnError(e)
                }))
    }
}