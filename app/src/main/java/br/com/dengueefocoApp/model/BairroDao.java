package br.com.dengueefocoApp.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface BairroDao {
    @Query ("SELECT * FROM bairro")
    Single<List<Bairro>> getAll();

    @Query ("SELECT * FROM bairro WHERE id = :id")
    Bairro loadAllById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Bairro... antivetorials);

    @Delete
    void delete(Bairro antivetorials);
}
