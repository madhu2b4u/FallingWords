package com.game.babbel.game.domain

import androidx.lifecycle.LiveData
import com.game.babbel.common.Result
import com.game.babbel.game.data.models.WordsListModelItem

interface GameUseCase {
    suspend fun getWords(): LiveData<Result<List<WordsListModelItem>>>

}