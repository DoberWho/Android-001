package cpr.castelao.aplicacinbasica.model.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class TipoAlimento  extends RealmObject {

    @PrimaryKey
    private long id;

    private String nombre;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
