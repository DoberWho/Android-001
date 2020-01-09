package cpr.castelao.aplicacinbasica.services;

import cpr.castelao.aplicacinbasica.model.ListaPokemon;
import cpr.castelao.aplicacinbasica.model.PerfilPokemon;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokeApiRetrofit {

    //offset = pagina * limit //<= NOTA: Forma correcta de paginacion
    final int LIMITE = 20;

    @GET("pokemon") // HOST+pokemon
    Call<ListaPokemon> listaCompleta();

    @GET("pokemon?offset={offset}&limit="+LIMITE)
    Call<ListaPokemon> listaPaginada(@Path("offset") int offset);

    @GET("pokemon/{id}/") // HOST+pokemon/id
    Call<PerfilPokemon> perfilPokemon(@Path("id") int idPokemon);

    @GET("generation") // HOST+generation
    Call<ListaPokemon> listaGeneraciones();

}
