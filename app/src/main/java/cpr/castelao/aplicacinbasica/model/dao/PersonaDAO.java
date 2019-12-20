package cpr.castelao.aplicacinbasica.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import cpr.castelao.aplicacinbasica.model.Persona;

@Dao
public interface  PersonaDAO {

    @Query("SELECT * FROM persona")
    List<Persona> getAll();

    @Query("SELECT * FROM persona WHERE uid IN (:userIds)")
    List<Persona> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM persona WHERE name LIKE :first AND " +
            "trabajo LIKE :last LIMIT 1")
    Persona findByName(String first, String last);

    @Insert
    void insertAll(Persona... users);

    @Delete
    void delete(Persona user);
}
