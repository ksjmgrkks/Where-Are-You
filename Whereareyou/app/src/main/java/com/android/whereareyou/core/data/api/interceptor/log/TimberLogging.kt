package com.android.whereareyou.core.data.api.interceptor.log

import timber.log.Timber

class TimberLogging: Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String? {
        return "(${element.fileName}:${element.lineNumber}) on ${element.methodName}"
    }
}