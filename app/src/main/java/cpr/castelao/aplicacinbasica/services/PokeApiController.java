package cpr.castelao.aplicacinbasica.services;

import java.io.IOException;

import cpr.castelao.aplicacinbasica.R;
import cpr.castelao.aplicacinbasica.model.ListaPokemon;
import cpr.castelao.aplicacinbasica.model.PerfilPokemon;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokeApiController {

    private final String HOST = "https://pokeapi.co/api/v2/";
    private Retrofit retrofit;
    private PokeApiRetrofit service;

    public PokeApiController(){
        retrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(PokeApiRetrofit.class);
    }

    public void todosLosPokemones(Callback<ListaPokemon> callback) throws IOException {
        Call<ListaPokemon> call = service.listaCompleta();

        call.enqueue(callback);
    }

    public void pokemonesPaginados(int pagina, Callback<ListaPokemon> callback) throws IOException {
        if (pagina <= 0){
            pagina = 1;
        }
        pagina = (pagina - 1) * PokeApiRetrofit.LIMITE;
        Call<ListaPokemon> call = service.listaPaginada(pagina);

        call.enqueue(callback);
    }

    public PerfilPokemon pokemon(int id){
        return null;
    }

    public ListaPokemon generaciones(){
        return null;
    }
}
