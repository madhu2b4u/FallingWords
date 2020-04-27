package com.game.babbel.game.data.repository


import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.game.babbel.game.data.models.WordsListModelItem
import javax.inject.Inject
import com.game.babbel.common.Result
import com.game.babbel.game.data.localsource.WordsLocalDataSource

class GameRepositoryImpl @Inject constructor(private val localDataSource: WordsLocalDataSource) : GameRepository {

    override suspend fun getWords() = liveData {

        emit(Result.loading())
        try {
            val response = localDataSource.getWords()
            emit(Result.success(response))

        } catch (exception: Exception) {
            emit(Result.error(exception.message ?: ""))
        }
    }


}