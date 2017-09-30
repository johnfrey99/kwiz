package com.physiq.vitalink.monitor.domain

import io.reactivex.observers.DisposableCompletableObserver

class TestDisposableCompletableObserver : DisposableCompletableObserver() {
    override fun onError(e: Throwable) {}
    override fun onComplete() {}
}