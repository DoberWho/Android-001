package cpr.castelao.aplicacinbasica.model;

import java.util.ArrayList;

public class ListaPokemon {

    private int count = 0;
    private String next = "";
    private String previous = "";
    private ArrayList<ListaPokemonItem> results = new ArrayList<ListaPokemonItem>();

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public ArrayList<ListaPokemonItem> getResults() {
        return results;
    }

    public void setResults(ArrayList<ListaPokemonItem> results) {
        this.results = results;
    }


}
