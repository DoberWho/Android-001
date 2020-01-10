package cpr.castelao.aplicacinbasica.model;


import com.orm.SugarRecord;

import java.io.Serializable;

public class Persona  extends SugarRecord<Persona> implements Serializable {


    public int uid;

    public String name;

    public String trabajo;

    public String imagen;
}
