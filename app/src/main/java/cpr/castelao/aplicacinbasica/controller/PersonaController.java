package cpr.castelao.aplicacinbasica.controller;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import cpr.castelao.aplicacinbasica.model.Persona;

public class PersonaController {

    public interface Listener{
        void newPersonas(List<Persona> items);
    }

    private PersonaController(){}
    private static final PersonaController instance = new PersonaController();
    public static PersonaController init(){
        return instance;
    }

    /**
     * Esta función devuelve varias veces a través del callback un array de personas.
     * Lo hacemos así para simular que vamos a tener varias respuestas de un servidor; o que vamos
     * a tener varias respuestas a través de un mismo callback. De forma que tenemos que juntar
     * los datos en un solo array y actualizar el adaptador.
     * @param callback
     */
    public void getPersonas(Listener callback){
        for (int i = 0; i < 10; i++) {
            List<Persona> nItems = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                Persona persona = new Persona();
                persona.name = "P-"+i+"-"+j;
                persona.trabajo = "T-"+j;
                persona.imagen = "I-"+i;

                String pI = (i < 10)? "0"+i : ""+i;
                String pJ = (j < 10)? "0"+j : ""+j;
                persona.uid = Integer.valueOf(""+pI+pJ);
                nItems.add(persona);
            }
            callback.newPersonas(nItems);
        }
    }


     


}
