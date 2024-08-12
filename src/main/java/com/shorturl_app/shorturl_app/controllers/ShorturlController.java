package com.shorturl_app.shorturl_app.controllers;

import com.shorturl_app.shorturl_app.exceptions.CustomException;
import com.shorturl_app.shorturl_app.models.Shorturl;
import com.shorturl_app.shorturl_app.services.ShorturlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class ShorturlController {


    @Autowired
    private ShorturlService service;


    @PostMapping("/")
    public String generarShortUrl(@RequestBody String url, Model model) {
        String shortUrl = service.shortUrl(url);

        try {
            Shorturl shorturl = new Shorturl();
            shorturl.setUrl(url);
            shorturl.setShortUrl(shortUrl);
            shorturl.setFecha(new Date());
        } catch (Exception e) {
            new CustomException(e.getMessage());
        }

        model.addAttribute("urloriginal", url);
        model.addAttribute("shorturl", "http://localhost:8080" + shortUrl);

        return "result";

    }


    @GetMapping("/listofurls")
    public List<Shorturl> buscarTodas() {

        List<Shorturl> list = null;

        try {
            list = service.mostrarTodas();
        } catch (Exception e) {
            new CustomException(e.getMessage());
        }

        return list;

    }

    @GetMapping("/listofurls")
    public List<Shorturl> buscarPorSitio(@RequestParam String sitio) {

        List<Shorturl> list = null;

        try {
            list = service.buscarPorSitio(sitio);
        } catch (Exception e) {
            new CustomException(e.getMessage());
        }

        return list;
    }

    @GetMapping("listofurls")
    public List<Shorturl> buscarPorFecha(@RequestParam Date fecha) {
        List<Shorturl> list = null;

        try {
            list = service.buscarPorFecha(fecha);
        } catch (Exception e) {
            new CustomException(e.getMessage());
        }

        return list;
    }

    @GetMapping("listofurls")
    public Optional<Shorturl> buscarPorId(@RequestParam Long id) {
        Optional<Shorturl> shorturl = null;

        try {
            shorturl = service.buscarPorId(id);
        } catch (Exception e) {
            new CustomException(e.getMessage());
        }

        return shorturl;
    }

}
