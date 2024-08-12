package com.shorturl_app.shorturl_app.services;

import com.shorturl_app.shorturl_app.models.Shorturl;
import com.shorturl_app.shorturl_app.repository.ShorturlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ShorturlServiceImpl implements ShorturlService{


    @Autowired
    private ShorturlRepository repository;

    private final String BASE_URL = "http://localhost:8080";


    // Genera la url recortada
    public String shortUrl(String urlOriginal) {
        String shortUrlCode = generateShortCode();
        String shortUrl = BASE_URL + shortUrlCode;

        Shorturl shorturl = new Shorturl();
        shorturl.setUrl(urlOriginal);
        shorturl.setShortUrl(shortUrl);

        repository.save(shorturl);
        return shortUrl;
    }

    // Acopla la url base con la shortUrl
    private String generateShortCode() {
        String characters = "ABCDEFGHIJKLNMOPQRSTUVWXZabcdefghijklnmopqrstuvwxyz";
        Random random = new Random();
        StringBuilder shortCode = new StringBuilder();
        for(int i = 0; i < 6; i++) {
            shortCode.append(characters.charAt(random.nextInt(characters.length())));
        }
        return shortCode.toString();
    }


    @Override
    public List<Shorturl> mostrarTodas() {
        return repository.findAll();
    }

    @Override
    public Optional<Shorturl> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Shorturl> buscarPorSitio(String sitio) {
        return repository.buscarPorSitio(sitio);
    }

    @Override
    public List<Shorturl> buscarPorFecha(Date date) {
        return repository.buscarPorFecha(date);
    }
}
