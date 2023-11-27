package com.br.digital_hoteis.domain.repository;


import com.br.digital_hoteis.domain.entity.Category;
import com.br.digital_hoteis.domain.entity.Hotel;
import com.br.digital_hoteis.domain.entity.RatingEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID>, JpaSpecificationExecutor<Category> {

    @Query("SELECT c FROM Category c WHERE c.id = :id")
    Category findCategoryById(UUID id);

    @Query("SELECT c.hotels FROM Category c WHERE c.id = :categoryId")
    Page<Hotel> findHotelsByCategoryId(@Param("categoryId") UUID categoryId, Pageable pageable);

    @Modifying
    @Query("UPDATE Category c SET " +
            "c.ratings = COALESCE(:ratings, c.ratings), " +
            "c.description = COALESCE(:description, c.description), " +
            "c.image_url = COALESCE(:image_url, c.image_url) " +
            "WHERE c.id = :id")
    void updateCategoryById(
            @Param("id") UUID id,
            @Param("ratings") RatingEnum ratings,
            @Param("description") String description,
            @Param("image_url") String image_url);



    @Modifying
    @Query("DELETE FROM Category c WHERE c.id = :id")
    void deleteCategoryById(@Param("id") UUID id);

}
