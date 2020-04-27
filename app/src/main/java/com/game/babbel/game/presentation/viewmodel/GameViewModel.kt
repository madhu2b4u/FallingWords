package com.game.babbel.game.presentation.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.game.babbel.common.Constants
import com.game.babbel.common.Result
import com.game.babbel.game.data.models.QuestionsModel
import com.game.babbel.game.data.models.WordsListModelItem
import com.game.babbel.game.domain.GameUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject


class GameViewModel @Inject constructor(private val mGameUseCase: GameUseCase) : ViewModel() {

    val wordsResult = MediatorLiveData<Result<List<WordsListModelItem>>>()
    var listOfWords = ArrayList<WordsListModelItem>()

    var score = 0
    var lives = Constants.LIVE_COUNT

    //current correct word
    var currentWordIndex = 0

    /**
     * QuestionModel: represent the (word & translation) that appear to user
     * and the translation of QuestionModel maybe correct or wrong
     */
    private lateinit var currentQuestion: QuestionsModel

    val questionLiveData = MutableLiveData<QuestionsModel>()
    val scoreLiveData = MutableLiveData<Int>(score)
    val livesLiveData = MutableLiveData<Int>(lives)
    val errorLiveData = MutableLiveData<String>()

    //notify the view when the game is over and passing the final score to display
    val gameOverLiveData = MutableLiveData<Int>()


    init {
        fetchWords()
    }

    private fun fetchWords() {
        viewModelScope.launch {
            wordsResult.addSource(mGameUseCase.getWords()) {
                wordsResult.value = it
            }
        }
    }

    fun onWrongAnswer(){
        livesLiveData.value = --lives
        sendNewWord()
    }

    fun onCorrectTransaltionClicked(){
        if(currentQuestion.translation == listOfWords[currentWordIndex].textSpa){
            score += 10
            scoreLiveData.value = score
        }
        else {
            livesLiveData.value = --lives
        }

        sendNewWord()
    }

    //If user select the current question translation is wrong

    fun onWrongTranslationClicked(){
        if(currentQuestion.translation != listOfWords[currentWordIndex].textSpa){
            score += 10
            scoreLiveData.value = score
        }
        else {
            livesLiveData.value = --lives
        }

        sendNewWord()
    }

    /**
     * this function is to load question and pass it to the view
     * and  also validating game over or not
     */
    fun sendNewWord(){
        //if user finished all words or lost all lives
        if(currentWordIndex >= listOfWords.size || lives<= 0){
            gameOverLiveData.value = score
            return
        }

        currentWordIndex++

        if(currentWordIndex == listOfWords.size-1){
            //handles last question in the list
            //use the correct translation
            QuestionsModel(listOfWords[currentWordIndex].textEng.toString(),listOfWords[currentWordIndex].textSpa.toString())
        }
        else {
            //generate random number to decide to show the correct translation or wrong one
            val rnd = if(listOfWords.size > 1) (0 until listOfWords.size).random() else 0
            currentQuestion = if(rnd%2 == 0){
                //use the correct translation
                QuestionsModel(listOfWords[currentWordIndex].textEng.toString(),listOfWords[currentWordIndex].textSpa.toString())
            }
            else {
                //use wrong translation
                QuestionsModel(listOfWords[currentWordIndex].textEng.toString(),listOfWords[rnd].textSpa.toString())
            }
        }

        questionLiveData.value = currentQuestion
    }



}