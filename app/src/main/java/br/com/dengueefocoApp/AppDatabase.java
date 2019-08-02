package br.com.dengueefocoApp;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Context;
import br.com.dengueefocoApp.model.Antivetorial;
import br.com.dengueefocoApp.model.AntivetorialDao;

@Database(entities = {Antivetorial.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
	public abstract AntivetorialDao antivetorialDao();

	private static AppDatabase INSTANCE = null;

	public static AppDatabase newInstance(Context context) {
		if (INSTANCE == null) {
			INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "dengueefocodb")
					.allowMainThreadQueries()
					.build();
		}
		return INSTANCE;
	}

	public static void destroyInstance() {
		INSTANCE = null;
	}

}
