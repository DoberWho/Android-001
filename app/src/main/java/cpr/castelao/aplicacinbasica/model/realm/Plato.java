package cpr.castelao.aplicacinbasica.model.realm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Plato extends RealmObject {


    private Date created;

    private RealmList<TipoAlimento> alimentos;


    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public List<TipoAlimento> getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(RealmList<TipoAlimento> alimentos) {
        this.alimentos = alimentos;
    }

    public static Plato getDbObject(){
        Realm realm = RealmController.init().get();
        realm.beginTransaction();
        Plato item = realm.createObject(Plato.class);
        realm.commitTransaction();
        return item;
    }
}
