package br.com.dengueefocoApp.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface RegistroDiarioDao {

    @Query ("SELECT * FROM RegistroDiario")
    Single<List<RegistroDiario>> getAll();

    @Query ("SELECT * FROM antivetorial WHERE id = :id")
    RegistroDiario loadAllById(int id);

    @Insert
    void insertAll(RegistroDiario... antivetorials);

    @Delete
    void delete(RegistroDiario antivetorials);

}
