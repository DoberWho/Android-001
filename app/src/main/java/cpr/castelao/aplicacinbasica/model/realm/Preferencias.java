package cpr.castelao.aplicacinbasica.model.realm;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Preferencias extends RealmObject {

    private Date created;

    private TipoAlimento tipoPreferido;

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

    public static Preferencias getDbObject(){
        Realm realm = RealmController.init().get();
        realm.beginTransaction();
        Preferencias item = realm.createObject(Preferencias.class);
        realm.commitTransaction();
        return item;
    }
}
