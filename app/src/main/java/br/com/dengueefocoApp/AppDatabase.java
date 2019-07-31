package br.com.dengueefocoApp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import android.content.Context;
import br.com.dengueefocoApp.model.Antivetorial;
import br.com.dengueefocoApp.model.AntivetorialDao;

@Database(entities = {Antivetorial.class}, version = 2)
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
