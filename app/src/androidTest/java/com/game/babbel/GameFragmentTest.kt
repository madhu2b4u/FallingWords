package com.game.babbel

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import androidx.test.runner.AndroidJUnit4
import com.android21buttons.fragmenttestrule.FragmentTestRule
import com.game.babbel.common.Constants
import com.game.babbel.game.presentation.ui.fragments.GameFragment
import com.game.babbel.game.presentation.viewmodel.GameViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi

class GameFragmentTest {

    //disable animation with Espresso testing
    @get:Rule
    val animationsRule = AnimationsRule()
    lateinit var viewModeTest: GameViewModel

    @get:Rule
    var fragmentTestRule: FragmentTestRule<*, GameFragment> =
        FragmentTestRule.create(
            GameFragment::class.java
        )


    @Before
    fun setUp() {
        viewModeTest = fragmentTestRule.fragment.mGameViewModel
    }

    @Test
    fun scoreAndLives_initialCorrectValuesDisplay() {
        onView(withId(R.id.tvScore)).check(matches(withText("0")))
        onView(withId(R.id.tvLives)).check(matches(withText(Constants.LIVE_COUNT.toString())))
    }

    @Test
    fun testDisplayDefault() {
        val question = viewModeTest.questionLiveData.value

        onView(withId(R.id.tvWord))
            .check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tvWord))
            .check(matches(withText(question?.question)))

        onView(withId(R.id.tvTranslation))
            .check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tvTranslation))
            .check(matches(withText(question?.translation)))
    }

    @Test
    fun testCorrectAnswer() {
        val currentWord = viewModeTest.listOfWords[viewModeTest.currentWordIndex]
        val question = viewModeTest.questionLiveData.value

        if(question?.translation == currentWord.textSpa){
            onView(withId(R.id.btnCorrect))
                .perform(ViewActions.click())
        }
        else {
            onView(withId(R.id.btnWrong))
                .perform(ViewActions.click())
        }

        val score = viewModeTest.scoreLiveData.value
        onView(withId(R.id.tvScore))
            .check(matches(withText(score.toString())))
    }

    @Test
    fun testWrongAnswer() {
        val currentWord = viewModeTest.listOfWords[viewModeTest.currentWordIndex]
        val question = viewModeTest.questionLiveData.value

        if(question?.translation == currentWord.textSpa){
            onView(withId(R.id.btnWrong))
                .perform(ViewActions.click())
        }
        else {
            onView(withId(R.id.btnCorrect))
                .perform(ViewActions.click())
        }

        onView(withId(R.id.tvLives))
            .check(matches(withText((Constants.LIVE_COUNT-1).toString())))
    }



}