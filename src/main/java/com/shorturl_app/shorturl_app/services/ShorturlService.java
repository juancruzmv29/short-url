package com.shorturl_app.shorturl_app.services;

import com.shorturl_app.shorturl_app.models.Shorturl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ShorturlService {


    public List<Shorturl> mostrarTodas();

    public Optional<Shorturl> buscarPorId(Long id);

    public List<Shorturl> buscarPorSitio(String sitio);

    public List<Shorturl> buscarPorFecha(Date date);

    public String shortUrl(String url);

}
