package br.com.dengueefocoApp.util;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import br.com.dengueefocoApp.AppDatabase;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BairroWorker extends Worker {

	private String TAG = BairroWorker.class.getSimpleName();

	public BairroWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
		super(context, workerParams);
	}

	@NonNull
	@Override
	public Result doWork() {
		try {
			InputStream is = getApplicationContext().getAssets().open("parametroerp.json");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuilder builder = new StringBuilder();
			while ((line = br.readLine()) != null) {
				builder.append(line);
			}
//            List<Bairro> bairro = new Gson().fromJson(builder.toString(), ArrayList<Bairro>.class);
			AppDatabase database = AppDatabase.getInstance(getApplicationContext());
//            database.bairroDao().insert(parametroLocals)
			is.close();
			br.close();
			return Result.success();
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			return Result.failure();

		}
	}

}
