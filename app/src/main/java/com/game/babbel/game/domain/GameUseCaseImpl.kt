package com.game.babbel.game.domain

import com.game.babbel.game.data.repository.GameRepository
import javax.inject.Inject

class GameUseCaseImpl @Inject constructor(private val mGameRepository: GameRepository) :
    GameUseCase {
    override suspend fun getWords()= mGameRepository.getWords()

}
