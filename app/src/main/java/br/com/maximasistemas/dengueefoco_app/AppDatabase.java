package br.com.maximasistemas.dengueefoco_app;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import br.com.maximasistemas.dengueefoco_app.model.Antivetorial;
import br.com.maximasistemas.dengueefoco_app.model.AntivetorialDao;

@Database (entities = {Antivetorial.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AntivetorialDao antivetorialDao();

}