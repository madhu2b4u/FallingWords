package com.game.babbel.game.di

import com.game.babbel.game.data.repository.GameRepository
import com.game.babbel.game.data.repository.GameRepositoryImpl
import com.game.babbel.game.domain.GameUseCaseImpl
import com.game.babbel.game.domain.GameUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class GameDomainModule {

    @Binds
    abstract fun bindsRepository(
        repoImpl: GameRepositoryImpl
    ): GameRepository


    @Binds
    abstract fun bindsGameUseCase(
        mGameUseCase: GameUseCaseImpl
    ): GameUseCase


}