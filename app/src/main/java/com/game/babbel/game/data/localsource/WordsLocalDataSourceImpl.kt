package com.game.babbel.game.data.localsource

import android.content.Context
import com.game.babbel.R
import com.game.babbel.game.data.models.WordsListModel
import com.game.babbel.game.data.models.WordsListModelItem
import com.google.gson.Gson
import javax.inject.Inject

class WordsLocalDataSourceImpl @Inject constructor(private val context: Context) : WordsLocalDataSource{

    /**
     * Load words from resources
     */
    override suspend fun getWords(): List<WordsListModelItem> {
        val inputStream = context.resources.openRawResource(R.raw.words)
        val buffer = ByteArray(inputStream.available())
        while (inputStream.read(buffer) !== -1);
        val json = String(buffer)
        val list = Gson().fromJson(json, WordsListModel::class.java)
        return (list)
    }

}