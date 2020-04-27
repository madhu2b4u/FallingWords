package com.game.babbel.game.data.repository

import androidx.lifecycle.LiveData
import com.game.babbel.common.Result
import com.game.babbel.game.data.models.WordsListModelItem


interface GameRepository {
    suspend fun getWords(): LiveData<Result<List<WordsListModelItem>>>

}