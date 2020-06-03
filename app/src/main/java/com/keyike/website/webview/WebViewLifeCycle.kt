package com.keyike.website.webview

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * @author juggist
 * @date 2020/5/12 22:09
 */
interface WebViewLifeCycle :LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPaused()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResumed()
}