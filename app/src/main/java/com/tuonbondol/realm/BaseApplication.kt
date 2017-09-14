package com.tuonbondol.realm

import android.app.Application
import io.realm.Realm

/***
 *
 * @author TUON BONDOL
 *
 */

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
    }
}