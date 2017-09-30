package com.physiq.vitalink.monitor.domain

import io.reactivex.observers.DisposableObserver

class TestDisposableObserver<T> : DisposableObserver<T>() {

    val values = mutableListOf<T>()

    override fun onStart() {
        super.onStart()
    }

    override fun onComplete() {
    }

    override fun onError(e: Throwable) {
    }

    override fun onNext(t: T) {
        values.add(t)
    }
}