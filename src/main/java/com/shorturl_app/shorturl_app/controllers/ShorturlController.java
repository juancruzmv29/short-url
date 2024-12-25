package com.shorturl_app.shorturl_app.controllers;

import com.shorturl_app.shorturl_app.exceptions.CustomException;
import com.shorturl_app.shorturl_app.models.Shorturl;
import com.shorturl_app.shorturl_app.services.ShorturlService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/")
public class ShorturlController {


    @Autowired
    private ShorturlService service;


    // genera una short url
    @PostMapping("/")
    public String generarShortUrl(@RequestParam String url, Model model, BindingResult result) {
        // El servicio generará una url
        String shortCode = service.shortUrl(url);

        try {

            if(result.hasErrors()) {
                model.addAttribute("titulo", "Resultado form");
                return "/";
            }

            Shorturl shorturl = new Shorturl();
            shorturl.setUrl(url);
            shorturl.setShortUrl(shortCode);
            shorturl.setFecha(new Date());
        } catch (Exception e) {
            new CustomException(e.getMessage());
        }

        // Agregamos el nombre del atributo y el valor del atributo
        model.addAttribute("urloriginal", url);
        model.addAttribute("shortUrl", "http://localhost:8080" + shortCode);

        return "/";
    }

    @GetMapping("/")
    public String limpiarCampos(Model model) {
        Shorturl shortUrlVacia = new Shorturl();

        shortUrlVacia.setId(null);
        shortUrlVacia.setFecha(null);
        shortUrlVacia.setUrl("");
        shortUrlVacia.setShortUrl("");

        // model.addAtributte("campo_vacio", shortUrlVacia);

        return "/";

    }


    // lista todas las urls generadas
    @GetMapping("/listofurls")
    public String buscarTodas(Model model) {

        List<Shorturl> list = null;

        try {
            list = service.mostrarTodas();
        } catch (Exception e) {
            new CustomException(e.getMessage());
        }

        model.addAttribute("urls", list);

        return "/listofurls";
    }

    // Buscan las urls recortada que se generaron por sitio
    @GetMapping("/listofurls")
    public String buscarPorSitio(@RequestParam() String sitio, Model model) {

        List<Shorturl> list = null;

        try {
            list = service.buscarPorSitio(sitio);
        } catch (Exception e) {
            new CustomException(e.getMessage());
        }

        model.addAttribute("urlsSitio", list);
        model.addAttribute("sitio", sitio);

        return "/listofurls";
    }

    // Se va a buscar una url por fecha
    /*
    @GetMapping("listofurls")
    public List<Shorturl> buscarPorFecha(@RequestParam Date fecha) {
        List<Shorturl> list = null;

        try {
            list = service.buscarPorFecha(fecha);
        } catch (Exception e) {
            new CustomException(e.getMessage());
        }

        return list;
    }*/

}
