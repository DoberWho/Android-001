package cpr.castelao.aplicacinbasica.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Persona implements Serializable {

    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "trabajo")
    public String trabajo;

    @ColumnInfo(name = "imagen")
    public String imagen;
}
