package br.com.dengueefocoApp;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Context;
import br.com.dengueefocoApp.model.Antivetorial;
import br.com.dengueefocoApp.model.AntivetorialDao;
import br.com.dengueefocoApp.model.RegistroDiario;
import br.com.dengueefocoApp.model.RegistroDiarioDao;

@Database(entities = {Antivetorial.class, RegistroDiario.class}, version = 5)
public abstract class AppDatabase extends RoomDatabase {
	public abstract AntivetorialDao antivetorialDao();
	public abstract RegistroDiarioDao registroDiarioDao();

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
