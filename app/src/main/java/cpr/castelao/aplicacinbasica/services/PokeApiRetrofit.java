package cpr.castelao.aplicacinbasica.services;

import cpr.castelao.aplicacinbasica.model.ListaPokemon;
import cpr.castelao.aplicacinbasica.model.PerfilPokemon;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokeApiRetrofit {

    @GET("pokemon")
    Call<ListaPokemon> listaCompleta();

    @GET("pokemon/{id}/")
    Call<PerfilPokemon> perfilPokemon(@Path("id") int idPokemon);

    @GET("generation")
    Call<ListaPokemon> listaGeneraciones();

}
