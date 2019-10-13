package br.com.dengueefocoApp.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Single;

import java.util.List;

@Dao
public interface AntivetorialDao {
    @Query ("SELECT * FROM antivetorial")
    Single<List<Antivetorial>> getAll();

    @Query ("SELECT * FROM antivetorial WHERE status = :status")
    Single<List<Antivetorial>> getAllByStatus(String status);

    @Query ("SELECT * FROM antivetorial WHERE id = :id")
    Antivetorial loadAllById(int id);

    @Insert
    void insertAll(Antivetorial... antivetorials);

    @Delete
    void delete(Antivetorial antivetorials);

}