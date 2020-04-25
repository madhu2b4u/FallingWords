package com.game.babbel.di

import com.game.babbel.game.presentation.ui.fragments.GameFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributesGameFragment(): GameFragment


}