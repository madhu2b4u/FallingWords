package com.game.babbel.game

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.game.babbel.LiveDataTestUtil
import com.game.babbel.MainCoroutineRule
import com.game.babbel.TestUtils
import com.game.babbel.game.data.models.WordsListModelItem
import com.game.babbel.game.domain.GameUseCase
import com.game.babbel.game.presentation.viewmodel.GameViewModel
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.game.babbel.common.Result
import com.game.babbel.common.Status


@ExperimentalCoroutinesApi
class GameViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()
    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    lateinit var viewModel: GameViewModel
    lateinit var useCase: GameUseCase

    @Before
    fun init() {
        //empty
    }

    @Test
    fun testCategoryLoadingData() = mainCoroutineRule.runBlockingTest {
        useCase = mock {
            onBlocking { getWords() } doReturn object :
                LiveData<Result<List<WordsListModelItem>>>() {
                init {
                    value = Result.loading()
                }
            }
        }

        viewModel = GameViewModel(useCase)
        val result = viewModel.wordsResult
        result.observeForever {}
        kotlinx.coroutines.delay(2000)
        assert(LiveDataTestUtil.getValue(viewModel.wordsResult).status == Status.LOADING)

    }

    @Test
    fun testCategorySuccessData() = mainCoroutineRule.runBlockingTest {
        useCase = mock {
            onBlocking { getWords() } doReturn object :
                LiveData<Result<List<WordsListModelItem>>>() {
                init {
                    value = Result.success(TestUtils().fakeList)
                }
            }
        }

        viewModel = GameViewModel(useCase)
        val result = viewModel.wordsResult
        result.observeForever {}
        kotlinx.coroutines.delay(2000)
        assert(
            LiveDataTestUtil.getValue(result).status == Status.SUCCESS &&
                    LiveDataTestUtil.getValue(result).data == TestUtils().fakeList
        )
    }

    @Test
    fun testCategoryErrorData() = mainCoroutineRule.runBlockingTest {
        useCase = mock {
            onBlocking { getWords() } doReturn object :
                LiveData<Result<List<WordsListModelItem>>>() {
                init {
                    value = Result.error("error")
                }
            }
        }

        viewModel = GameViewModel(useCase)
        val result = viewModel.wordsResult
        result.observeForever {}
        kotlinx.coroutines.delay(2000)
        assert(
            LiveDataTestUtil.getValue(result).status == Status.ERROR &&
                    LiveDataTestUtil.getValue(result).message == "error"
        )
    }




}