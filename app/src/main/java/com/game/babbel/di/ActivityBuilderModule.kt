package com.game.babbel.di

import com.game.babbel.MainActivity
import com.game.babbel.game.presentation.ui.activity.GameActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector
    internal abstract fun contributesMainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun contributesGameActivity(): GameActivity

}