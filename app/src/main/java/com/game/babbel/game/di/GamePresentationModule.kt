package com.game.babbel.game.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.game.babbel.ViewModelFactory
import com.game.babbel.di.ViewModelKey
import com.game.babbel.game.presentation.viewmodel.GameViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class GamePresentationModule {
    @Binds
    abstract fun bindsViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(GameViewModel::class)
    abstract fun bindsGameViewModel(mGameViewModel: GameViewModel): ViewModel
}