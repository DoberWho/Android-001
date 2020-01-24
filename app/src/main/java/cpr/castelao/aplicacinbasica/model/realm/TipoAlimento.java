package cpr.castelao.aplicacinbasica.model.realm;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class TipoAlimento  extends RealmObject {

    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static TipoAlimento getDbObject(){
        Realm realm = RealmController.init().get();
        realm.beginTransaction();
        TipoAlimento item = realm.createObject(TipoAlimento.class);
        realm.commitTransaction();
        return item;
    }

    public static TipoAlimento save(TipoAlimento tipo){
        Realm realm = RealmController.init().get();
        realm.beginTransaction();
        TipoAlimento item = realm.createObject(TipoAlimento.class);
        item.setNombre(tipo.getNombre());
        realm.commitTransaction();
        return item;
    }
}
