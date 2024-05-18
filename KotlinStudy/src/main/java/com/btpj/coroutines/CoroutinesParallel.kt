package com.btpj.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.sync.withPermit
import kotlinx.coroutines.withContext
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.locks.ReentrantLock

/**
 * 协程并行操作
 *
 * @author LTP  2023/5/25
 */
class CoroutinesParallel {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            CoroutinesParallel().apply {
                testWithContext()
                testCoroutineScope()
                testAsync()
            }
        }
    }

    @Test
    fun testWithContext() {
        runBlocking {
            val start = System.currentTimeMillis()
            withContext(Dispatchers.IO) {
                launch {
                    delay(1000)
                    println("withContext线程：${Thread.currentThread().name}")
                }
                launch { delay(2000) }
            }
            println("testWithContext耗时为：${System.currentTimeMillis() - start}")
        }
    }

    @Test
    fun testCoroutineScope() {
        runBlocking {
            val start = System.currentTimeMillis()
            coroutineScope {
                launch {
                    delay(1000)
                    println("testCoroutineScope线程：${Thread.currentThread().name}")
                }
                launch { delay(2000) }
            }
            println("testCoroutineScope耗时为：${System.currentTimeMillis() - start}")
        }
    }

    @Test
    fun testAsync() {
        runBlocking {
            val start = System.currentTimeMillis()
            val deferred1 = async {
                delay(1000)
                println("testAsync线程：${Thread.currentThread().name}")
                3
            }
            val deferred2 = async {
                delay(2000)
                4
            }
            val result = deferred1.await() + deferred2.await()
            println("testAsync耗时：${System.currentTimeMillis() - start}，$result")
        }
    }
}