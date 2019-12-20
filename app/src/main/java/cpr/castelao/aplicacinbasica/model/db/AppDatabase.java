package cpr.castelao.aplicacinbasica.model.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import cpr.castelao.aplicacinbasica.model.Persona;
import cpr.castelao.aplicacinbasica.model.dao.PersonaDAO;

@Database(entities = {Persona.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PersonaDAO userDao();
}