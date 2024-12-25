package com.shorturl_app.shorturl_app.repository;

import com.shorturl_app.shorturl_app.models.Shorturl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ShorturlRepository extends JpaRepository<Shorturl, Long> {


    @Query("SELECT s FROM Shorturl s WHERE s.url LIKE %:sitio%")
    List<Shorturl> buscarPorSitio(@Param("sitio") String sitio);

    /*
    @Query("SELECT Shorturl s FROM shorturls WHERE s.url = date")
    List<Shorturl> buscarPorFecha(@Param("date") Date date);
    */

}
