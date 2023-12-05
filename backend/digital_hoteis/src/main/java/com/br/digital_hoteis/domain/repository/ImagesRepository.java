package com.br.digital_hoteis.domain.repository;

import com.br.digital_hoteis.domain.entity.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ImagesRepository extends JpaRepository<Images, UUID> {
    @Modifying
    @Query("UPDATE Images i SET " +
            "i.title = CASE WHEN :title IS NULL THEN i.title ELSE :title END, " +
            "i.url = CASE WHEN :url IS NULL THEN i.url ELSE :url END " +
            "WHERE i.id = :id")
    void updateImagesById(
            @Param("id") UUID id,
            @Param("title") String title,
            @Param("url") String url);

    @Modifying
    @Query("DELETE FROM Images i WHERE i.id = :id")
    void deleteImagesById(@Param("id") UUID id);


}
