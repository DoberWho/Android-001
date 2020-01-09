package cpr.castelao.aplicacinbasica.model;

public class PerfilPokemon {

    private String id = "";
    private int height = 0;
    private String name = "";
    private PerfilPokemonImagenes sprites;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PerfilPokemonImagenes getSprites() {
        return sprites;
    }

    public void setSprites(PerfilPokemonImagenes sprites) {
        this.sprites = sprites;
    }
}
