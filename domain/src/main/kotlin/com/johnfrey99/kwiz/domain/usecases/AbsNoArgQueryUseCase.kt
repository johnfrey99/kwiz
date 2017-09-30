package com.johnfrey99.kwiz.domain.usecases

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.observers.DisposableObserver

/**
 * Abstraction for single unit of work from business logic standpoint. Purpose of use case
 * is to query for data.
 *
 * @param <Result> Result returned to the observer.
 */
abstract class AbsNoArgQueryUseCase<Result>(
        private val workScheduler: Scheduler,
        private val observeScheduler: Scheduler
) : AbsDisposableUseCase() {
    abstract fun buildUseCaseObservable(): Observable<Result>
    fun execute(doOnNext: (r: Result) -> Unit,
                doOnError: (e: Throwable) -> Unit = {},
                doOnComplete: () -> Unit = {}
    ) {
        addDisposable(buildUseCaseObservable()
                .subscribeOn(workScheduler)
                .observeOn(observeScheduler)
                .subscribeWith(object : DisposableObserver<Result>() {
                    override fun onNext(r: Result) = doOnNext(r)
                    override fun onError(e: Throwable) = doOnError(e)
                    override fun onComplete() = doOnComplete()
                })
        )
    }
}