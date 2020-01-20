package cpr.castelao.aplicacinbasica.services.controladoresPaginas;

import org.jsoup.nodes.Document;

import java.util.List;

import cpr.castelao.aplicacinbasica.model.Episodio;

public abstract class PaginaEpisodios {

    public String url;
    public abstract List<Episodio> getFromDocument(Document doc);
}
