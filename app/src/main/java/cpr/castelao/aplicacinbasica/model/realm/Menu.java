package cpr.castelao.aplicacinbasica.model.realm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Menu extends RealmObject {

       private Date created;

    private RealmList<Plato> platos;


    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public List<Plato> getPlatos() {
        return platos;
    }

    public void setPlatos(RealmList<Plato> platos) {
        this.platos = platos;
    }

    public static Menu getDbObject(){
        Realm realm = RealmController.init().get();
        realm.beginTransaction();
        Menu item = realm.createObject(Menu.class);
        realm.commitTransaction();
        return item;
    }
}
