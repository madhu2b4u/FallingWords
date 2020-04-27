package com.game.babbel.game

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.game.babbel.LiveDataTestUtil
import com.game.babbel.MainCoroutineRule
import com.game.babbel.TestUtils
import com.game.babbel.common.Status
import com.game.babbel.common.Result
import com.game.babbel.game.data.models.WordsListModelItem
import com.game.babbel.game.data.repository.GameRepository
import com.game.babbel.game.domain.GameUseCase
import com.game.babbel.game.domain.GameUseCaseImpl
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GameUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    lateinit var useCase: GameUseCase

    lateinit var repository: GameRepository

    @Test
    fun testWordsLoadingData() = mainCoroutineRule.runBlockingTest {
        repository = mock {
            onBlocking { getWords() } doReturn object :
                LiveData<Result<List<WordsListModelItem>>>() {
                init {
                    value = Result.loading()
                }
            }
        }
        useCase = GameUseCaseImpl(repository)

        val result = useCase.getWords()
        result.observeForever { }
        assert(LiveDataTestUtil.getValue(result).status == Status.LOADING)

    }


    @Test
    fun testWordsSuccessData() = mainCoroutineRule.runBlockingTest {
        repository = mock {
            onBlocking { getWords() } doReturn object :
                LiveData<Result<List<WordsListModelItem>>>() {
                init {
                    value = Result.success(TestUtils().fakeList)
                }
            }
        }
        useCase = GameUseCaseImpl(repository)

        val result = useCase.getWords()

        result.observeForever { }

        assert(
            LiveDataTestUtil.getValue(result).status == Status.SUCCESS && LiveDataTestUtil.getValue(
                result
            ).data == TestUtils().fakeList
        )

    }

    @Test
    fun testWordsErrorData() = mainCoroutineRule.runBlockingTest {
        repository = mock {
            onBlocking { getWords() } doReturn object :
                LiveData<Result<List<WordsListModelItem>>>() {
                init {
                    value = Result.error("error")
                }
            }
        }
        useCase = GameUseCaseImpl(repository)

        val result = useCase.getWords()
        result.observeForever { }
        assert(
            LiveDataTestUtil.getValue(result).status == Status.ERROR && LiveDataTestUtil.getValue(
                result
            ).message == "error"
        )

    }



}