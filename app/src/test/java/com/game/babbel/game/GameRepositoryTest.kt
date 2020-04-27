package com.game.babbel.game

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.game.babbel.LiveDataTestUtil
import com.game.babbel.MainCoroutineRule
import com.game.babbel.TestUtils
import com.game.babbel.common.Status
import com.game.babbel.game.data.localsource.WordsLocalDataSource
import com.game.babbel.game.data.localsource.WordsLocalDataSourceImpl
import com.game.babbel.game.data.repository.GameRepository
import com.game.babbel.game.data.repository.GameRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


@ExperimentalCoroutinesApi
class GameRepositoryTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var repository: GameRepository

    @Mock
    lateinit var localDataSource: WordsLocalDataSource


    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        repository = GameRepositoryImpl(localDataSource)
    }

    @Test
    fun testGetData() = mainCoroutineRule.runBlockingTest {
        Mockito.`when`(localDataSource.getWords()).thenReturn(TestUtils().fakeList)
        val result = repository.getWords()
        assert(LiveDataTestUtil.getValue(result).status == Status.LOADING)
        assert(LiveDataTestUtil.getValue(result).status == Status.SUCCESS)
        assert(LiveDataTestUtil.getValue(result).data == TestUtils().fakeList)
    }


    @Test(expected = Exception::class)
    fun testThrowException() = mainCoroutineRule.runBlockingTest {
        Mockito.doThrow(Exception("error")).`when`(localDataSource.getWords())
        repository.getWords()
    }


}
