package br.com.dengueefocoApp.util;

import android.content.Context;
import android.util.JsonReader;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.dengueefocoApp.AppDatabase;
import br.com.dengueefocoApp.model.Bairro;
import kotlin.text.Charsets;

public class BairroWorker extends Worker {

    private String TAG = BairroWorker.class.getSimpleName()

    public BairroWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            InputStream inputStream = getApplicationContext().getAssets().open("parametroerp.json");
            BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = bReader.readLine()) != null) {
                builder.append(line);
            }

            InputStreamReader reader = new InputStreamReader(inputStream, Charsets.UTF_8);
//            Bairro bairro = new Gson().fromJson(builder.toString(), List<Bairro>.class);
            AppDatabase database = AppDatabase.getInstance(getApplicationContext());
//            database.bairroDao().insert(parametroLocals)
            Result.success()
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return Result.failure();

        } finally {
//            if (jsonReader != null) {
//                try {
//                    jsonReader.close();
//                } catch (Exception e) {
//                    Log.e(TAG, e.getMessage());
//                }
//            }
        }
        return null;
    }
}
