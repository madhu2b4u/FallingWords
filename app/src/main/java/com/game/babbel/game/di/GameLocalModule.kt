package com.game.babbel.game.di

import com.game.babbel.game.data.localsource.WordsLocalDataSource
import com.game.babbel.game.data.localsource.WordsLocalDataSourceImpl

import dagger.Binds
import dagger.Module


@Module(includes = [GameLocalModule.Binders::class])
class GameLocalModule {

    @Module
    interface Binders {
        @Binds
        fun bindsLocalDataSource(
            localDataSourceImpl: WordsLocalDataSourceImpl
        ): WordsLocalDataSource
    }

}