package cpr.castelao.aplicacinbasica.model.realm;

import io.realm.Realm;

public class RealmController {

    Realm realm;

    private static RealmController instance = new RealmController();
    private RealmController(){
        realm = Realm.getDefaultInstance();
    }

    public static RealmController init(){
        return instance;
    }

    public Realm get(){
        return realm;
    }


}
