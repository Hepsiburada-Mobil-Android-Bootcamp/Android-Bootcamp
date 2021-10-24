package com.android.contentprovider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.android.camp.R
import kotlinx.coroutines.*

class CallActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)

        findViewById<View>(R.id.image_view_call_end).setOnClickListener {
            intent.putExtra("state", 1)
            setResult(RESULT_OK, intent)
            finish()
        }

        findViewById<View>(R.id.image_view_call_start).setOnClickListener {
            intent.putExtra("state", 2)
            setResult(RESULT_OK, intent)
            finish()
        }


        Log.d("CallActivityScope", "GlobalScope Start")


        GlobalScope.launch {
            delay(5000)
            Log.d("CallActivityScope", "GlobalScope Launch")

            Log.d(
                "CallActivityScope",
                "Normal Thread Name: ${Thread.currentThread().name}, context: $coroutineContext"
            )

            launch(Dispatchers.IO) {
                delay(5000)
                Log.d(
                    "CallActivityScopeJob",
                    "1. iş IO Thread Name: ${Thread.currentThread().name}, context: $coroutineContext"
                )

            }

            launch(Dispatchers.IO) {
                delay(3000)
                Log.d(
                    "CallActivityScopeJob",
                    "2. iş IO Thread Name: ${Thread.currentThread().name}, context: $coroutineContext"
                )

            }

            launch(Dispatchers.Main) {
                Log.d(
                    "CallActivityScope",
                    "Main Thread Name: ${Thread.currentThread().name}, context: $coroutineContext"
                )
            }

            launch(Dispatchers.Default) {
                Log.d(
                    "CallActivityScope",
                    "Default Thread Name: ${Thread.currentThread().name}, context: $coroutineContext"
                )
            }

            launch(Dispatchers.Unconfined) {
                Log.d(
                    "CallActivityScope",
                    "Unconfined Thread Name: ${Thread.currentThread().name}, context: $coroutineContext"
                )

                withContext(Dispatchers.Main) {
                    Log.d(
                        "CallActivityScope",
                        "Unconfined Main Thread Name: ${Thread.currentThread().name}, context: $coroutineContext"
                    )
                }
            }
        }

        Log.d("CallActivityScope", "GlobalScope End")


        Log.d("CallActivityScope", "runBlocking Start")

        runBlocking {  //Main thread bloklar.....
            val db = async { jobTest1() } // db create, instance 10000
            val aws = async { jobTest2() } // get 5000

            val result1 = aws.await()
            Log.d("CallActivityScope", "job 2 döndü")
            Log.d("CallActivityScope", "diğer işlemler yapılıyor.....")

            val result2 = db.await()
            Log.d("CallActivityScope", "job 1 döndü")

            Log.d("CallActivityScope", "job1 result: $result1, job2 result: $result2")
        }

        Log.d("CallActivityScope", "runBlocking End")
    }

    suspend fun jobTest1(): String {
        Log.d("CallActivityScope", "job 1 başladı")
        delay(5000)
        Log.d("CallActivityScope", "job 1 bitti")
        return "job1 bitti"
    }

    suspend fun jobTest2(): String {
        Log.d("CallActivityScope", "job 2 başladı")
        delay(3000)
        Log.d("CallActivityScope", "job 2 bitti")
        return "job2 bitti"
    }

    override fun onDestroy() {
        super.onDestroy()

    }

}
