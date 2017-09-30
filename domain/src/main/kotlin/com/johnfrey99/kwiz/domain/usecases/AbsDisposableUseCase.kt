package com.johnfrey99.kwiz.domain.usecases

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class AbsDisposableUseCase : Disposable {
    private val disposables: CompositeDisposable = CompositeDisposable()
    override fun dispose() { if (!disposables.isDisposed) disposables.dispose() }
    override fun isDisposed() = disposables.isDisposed
    fun addDisposable(disposable: Disposable) = disposables.add(disposable)
}