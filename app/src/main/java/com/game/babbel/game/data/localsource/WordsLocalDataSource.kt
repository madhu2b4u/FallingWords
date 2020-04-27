package com.game.babbel.game.data.localsource


import com.game.babbel.common.Result
import com.game.babbel.game.data.models.WordsListModelItem

/**
 * This represents wordlist data source interface.
 * Local type words list data-source should implement this interface
 */
interface WordsLocalDataSource {
    suspend fun getWords(): List<WordsListModelItem>
}