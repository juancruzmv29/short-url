package com.shorturl_app.shorturl_app.controllers;

import com.shorturl_app.shorturl_app.exceptions.CustomException;
import com.shorturl_app.shorturl_app.models.Shorturl;
import com.shorturl_app.shorturl_app.services.ShorturlService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/")
public class ShorturlController {


    @Autowired
    private ShorturlService service;


    // genera una short url
    @PostMapping("/")
    public String generarShortUrl(@RequestParam String url, Model model) {
        try {
            Shorturl shorturl = new Shorturl(url);

            // Generar short code
            String shortCode = service.shortUrl(url);
            shorturl.setShortUrl(shortCode);

            // Extraer dominio usando regex
            String sitioExtraido = "";
            Pattern pattern = Pattern.compile("(?<=www\\.)[^.]+(?=\\.com)");
            Matcher matcher = pattern.matcher(url);
            if (matcher.find()) {
                sitioExtraido = matcher.group();
            }
            shorturl.setSitio(sitioExtraido);


            //shorturl.setFecha(new Date());

            // Guardar en DB
            service.guardarUrl(shorturl);

            // Pasar objeto al modelo
            model.addAttribute("shorturl", shorturl);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al generar la URL corta");
        }

        return "index";
    }


    @GetMapping("/")
    public String limpiarCampos(Model model) {
        Shorturl shortUrlVacia = new Shorturl();

        shortUrlVacia.setId(null);
        //shortUrlVacia.setFecha(null);
        shortUrlVacia.setUrl("");
        shortUrlVacia.setShortUrl("");

        model.addAttribute("shorturl", shortUrlVacia);

        return "index";

    }

    @GetMapping("/listofurls")
    public String buscarPorSitio(@RequestParam(required = false) String sitio, Model model) {
        List<Shorturl> listaPorSitio;

        try {
            if (sitio != null && !sitio.isBlank()) {
                listaPorSitio = service.buscarPorSitio(sitio);
            } else {
                listaPorSitio = service.mostrarTodas();
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }

        model.addAttribute("listaPorSitio", listaPorSitio);
        model.addAttribute("sitio", sitio);
        return "listofurls";
    }





}
