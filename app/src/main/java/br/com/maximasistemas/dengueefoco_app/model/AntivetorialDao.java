package br.com.maximasistemas.dengueefoco_app.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import io.reactivex.Single;

import java.util.List;

@Dao
public interface AntivetorialDao {
    @Query ("SELECT * FROM antivetorial")
    Single<List<Antivetorial>> getAll();

    @Query ("SELECT * FROM antivetorial WHERE id = :id")
    Antivetorial loadAllById(int id);

    @Insert
    void insertAll(Antivetorial... antivetorials);

    @Delete
    void delete(Antivetorial antivetorials);

}
