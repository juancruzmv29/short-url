package com.shorturl_app.shorturl_app.repository;

import com.shorturl_app.shorturl_app.models.Shorturl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ShorturlRepository extends JpaRepository<Shorturl, Long> {


    @Query("SELECT Shorturl s FROM shorturls WHERE s.url := sitio")
    public List<Shorturl> buscarPorSitio(String url);

    @Query("SELECT Shorturl s FROM shorturls WHERE s.url := date")
    public List<Shorturl> buscarPorFecha(Date date);


}
