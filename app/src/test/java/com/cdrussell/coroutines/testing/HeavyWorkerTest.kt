package com.cdrussell.coroutines.testing

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HeavyWorkerTest {

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Test
    fun useRunBlocking() = runBlocking<Unit> {
        val heavyWorker = HeavyWorker()
        val expected = 666666671666
        val result = heavyWorker.heavyOperation()
        assertEquals(expected, result)
    }

    /**
     * This fails with an IllegalStateException: This job has not completed yet
     */
    @Test
    fun useRunBlockingTest() = runBlockingTest {
        val heavyWorker = HeavyWorker()
        val expected = 666666671666
        val result = heavyWorker.heavyOperation()
        assertEquals(expected, result)
    }

    @Test
    fun useTestCoroutineDispatcherRunBlockingTest() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val heavyWorker = HeavyWorker(coroutinesTestRule.testDispatcherProvider)
        val expected = 666666671666
        val result = heavyWorker.heavyOperation()
        assertEquals(expected, result)
    }

}