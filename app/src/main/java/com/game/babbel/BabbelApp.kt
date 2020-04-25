package com.game.babbel

import com.game.babbel.di.DaggerBabbelAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BabbelApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerBabbelAppComponent.builder().application(this).build()
    }


}