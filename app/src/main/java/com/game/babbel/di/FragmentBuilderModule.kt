package com.game.babbel.di

import com.game.babbel.game.presentation.ui.fragments.GameFragment
import com.game.babbel.game.presentation.ui.fragments.TutorialFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributesGameFragment(): GameFragment

    @ContributesAndroidInjector
    abstract fun contributesTutorialFragment(): TutorialFragment


}