package br.com.dengueefocoApp.util

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import br.com.dengueefocoApp.AppDatabase
import br.com.dengueefocoApp.model.Bairro
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader

class BairroWorker2(appContext: Context, workerParams: WorkerParameters) :
	Worker(appContext, workerParams) {

	private val TAG = BairroWorker2::class.java.simpleName

	override fun doWork(): Result {
		val pdvType = object : TypeToken<Set<Bairro>>() {}.type
		var jsonReader: JsonReader? = null

		return try {
			val inputStream = applicationContext.assets.open("parametroerp.json")
			jsonReader = JsonReader(inputStream.reader())
			val bairros: Set<Bairro> = Gson().fromJson(jsonReader, pdvType)
			val database = AppDatabase.getInstance(applicationContext)
			for (bairro in bairros) {
				database.bairroDao().insertAll(bairro)
			}
			Result.success()
		} catch (ex: Exception) {
			Log.e(TAG, "Error seeding database", ex)
			Result.failure()
		} finally {
			jsonReader?.close()
		}
	}

}