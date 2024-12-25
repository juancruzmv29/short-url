package com.shorturl_app.shorturl_app.services;

import com.shorturl_app.shorturl_app.models.Shorturl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ShorturlService {


    List<Shorturl> mostrarTodas();


    List<Shorturl> buscarPorSitio(String sitio);

    /*
    List<Shorturl> buscarPorFecha(Date date);
    */

    String shortUrl(String url);

}
