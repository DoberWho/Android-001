package cpr.castelao.aplicacinbasica.model;


import com.orm.SugarRecord;

public class Persona  extends SugarRecord<Persona> {


    public int uid;

    public String name;

    public String trabajo;

    public String imagen;
}
