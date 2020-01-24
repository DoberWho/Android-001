package cpr.castelao.aplicacinbasica.model.realm;

import java.util.ArrayList;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Plato extends RealmObject {

    @PrimaryKey
    private long id;

    private Date created;

    private ArrayList<TipoAlimento> alimentos;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public ArrayList<TipoAlimento> getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(ArrayList<TipoAlimento> alimentos) {
        this.alimentos = alimentos;
    }
}
