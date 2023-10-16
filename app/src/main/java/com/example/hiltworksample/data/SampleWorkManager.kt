package com.example.hiltworksample.data

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.example.hiltworksample.repository.SampleRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@HiltWorker
class SampleWorkManager @AssistedInject constructor (
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val repository: SampleRepository
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            try {
                val response = repository.getUserData()
                if(response.isSuccessful) {
                    print(response.body())
                }
                Result.success()
            } catch (exception: Exception) {
                exception.printStackTrace()
                Result.failure()
            }
        }
    }
}

fun startSampleWorkManager(context: Context) {
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    val workRequest = OneTimeWorkRequest.Builder(SampleWorkManager::class.java)
        .setConstraints(constraints)
        .build()

    WorkManager.getInstance(context).enqueue(workRequest)
}