package cpr.castelao.aplicacinbasica.services.controladoresPaginas;

import java.util.List;

import cpr.castelao.aplicacinbasica.model.Episodio;

public interface PaginaListener {
    void devolver(List<Episodio> items);
}
