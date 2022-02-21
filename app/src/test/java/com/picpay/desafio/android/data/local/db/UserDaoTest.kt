package com.picpay.desafio.android.data.local.db

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class UserDaoTest {

    private lateinit var database: ProjectDatabase
    private lateinit var userDao: UserDao

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testCoroutineDispatcher)

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setTestDispatcher() {
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @Before
    fun setup() {
        val context = RuntimeEnvironment.getApplication() as Context

        database = Room
            .inMemoryDatabaseBuilder(context, ProjectDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        userDao = database.userDao()
    }

    @After
    fun `database close` () {
        database.close()
    }

    @After
    fun `reset coroutines` () {
        Dispatchers.resetMain()
        testCoroutineScope.cleanupTestCoroutines()
    }

    @Test
    fun `insert then retrieve from db`() = testCoroutineScope.runBlockingTest {
        userDao.insertReplace(Mocks.userEntityMock)
        val userEntity = userDao.find(0)

        assertEquals(Mocks.userEntityMock, userEntity)
    }

    @Test
    fun `insert list then retrieve list from db`() = testCoroutineScope.runBlockingTest {

    }

    @Test
    fun `update then retrieve from db`() = testCoroutineScope.runBlockingTest {

    }

    @Test
    fun `update list then retrieve from db`() = testCoroutineScope.runBlockingTest {

    }

    @Test
    fun `delete all`() = testCoroutineScope.runBlockingTest {

    }

    @Test
    fun `delete by id`() = testCoroutineScope.runBlockingTest {

    }

    @Test
    fun `delete by ids`() = testCoroutineScope.runBlockingTest {

    }

}