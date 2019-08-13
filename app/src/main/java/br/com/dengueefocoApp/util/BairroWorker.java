package br.com.dengueefocoApp.util;

import android.content.Context;
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
import java.util.ArrayList;
import java.util.List;

import br.com.dengueefocoApp.AppDatabase;
import br.com.dengueefocoApp.model.Bairro;

public class BairroWorker extends Worker {

    private String TAG = BairroWorker.class.getSimpleName();

    public BairroWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            Type type = new TypeToken<ArrayList<Bairro>>() {
            }.getType();
            InputStream is = getApplicationContext().getAssets().open("bairros.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
            List<Bairro> bairros = new Gson().fromJson(builder.toString(), type);
            AppDatabase database = AppDatabase.getInstance(getApplicationContext());
            for (Bairro bairro : bairros) {
                database.bairroDao().insertAll(bairro);
            }
            is.close();
            br.close();
            return Result.success();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return Result.failure();

        }
    }

}
