package com.test.palmhrdemo

import com.test.palmhrdemo.repositories.BooksRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
@RunWith(Parameterized::class)
class BooksTests(private val q: String, private val maxResults: String,private val startIndex: String) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf("dubai", "20","2"),
            arrayOf("lahore", "10","15"),
            arrayOf("keyes", "40","4")
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun verifyBooksResult(): Unit = runTest {
        // Verify whether the end service working fine with different params
        val rep = BooksRepository()
        rep.getBooks(q,maxResults, startIndex) {
            assert(it.data?.items!= null)
        }
    }
}


