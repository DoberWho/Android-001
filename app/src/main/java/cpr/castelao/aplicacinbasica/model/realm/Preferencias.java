package cpr.castelao.aplicacinbasica.model.realm;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Preferencias extends RealmObject {

    @PrimaryKey
    private long id;

    private Date created;

    private TipoAlimento tipoPreferido;


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

    public TipoAlimento getTipoPreferido() {
        return tipoPreferido;
    }

    public void setTipoPreferido(TipoAlimento tipoPreferido) {
        this.tipoPreferido = tipoPreferido;
    }
}
