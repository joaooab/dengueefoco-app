package br.com.dengueefocoApp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import br.com.dengueefocoApp.model.Antivetorial;
import br.com.dengueefocoApp.model.AntivetorialDao;
import br.com.dengueefocoApp.model.Bairro;
import br.com.dengueefocoApp.model.BairroDao;
import br.com.dengueefocoApp.util.BairroWorker;

@Database (entities = {Antivetorial.class, Bairro.class}, version = 6)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AntivetorialDao antivetorialDao();

    public abstract BairroDao bairroDao();

    private static AppDatabase INSTANCE = null;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "dengueefocodb")
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            OneTimeWorkRequest request = new OneTimeWorkRequest
                                    .Builder(BairroWorker.class)
                                    .build();
                            WorkManager.getInstance().enqueue(request);
                        }
                    })
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}
