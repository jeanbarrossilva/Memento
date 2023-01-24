package com.jeanbarrossilva.aurelius.utils // ktlint-disable filename

import android.app.Activity
import android.app.Instrumentation
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage

/** Currently running [Activity]. **/
internal val Instrumentation.activity: Activity?
    get() {
        var activity: Activity? = null
        runOnMainSync {
            activity = ActivityLifecycleMonitorRegistry
                .getInstance()
                .getActivitiesInStage(Stage.RESUMED)
                .lastOrNull()
        }
        return activity
    }
