package com.auramislab.happypaws

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application entry and Hilt root (HAP-15).
 * Firebase / Room / sync wiring arrive in later Tasks — keep secrets out of this class.
 */
@HiltAndroidApp
class HappyPawsApp : Application()
