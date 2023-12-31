package com.br.digital_hoteis.app.api.controller;

import com.br.digital_hoteis.app.api.CategoryApi;
import com.br.digital_hoteis.app.api.dto.request.CreateCategoryRequest;
import com.br.digital_hoteis.app.api.dto.response.CategoryDetailedResponse;
import com.br.digital_hoteis.app.api.dto.response.CategorySummaryResponse;
import com.br.digital_hoteis.app.api.dto.response.HotelSummaryResponse;
import com.br.digital_hoteis.domain.entity.Category;
import com.br.digital_hoteis.domain.entity.Hotel;
import com.br.digital_hoteis.domain.service.CategoryService;
import com.br.digital_hoteis.domain.service.HotelService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Map;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class CategoryController implements CategoryApi {

    private final CategoryService categoryService;
    private final HotelService hotelService;


    @Override
    public ResponseEntity<Page<CategorySummaryResponse>> findCategories(Pageable page) {
        Page<Category> pageCategory = categoryService.listAllCategories(page);
        Page<CategorySummaryResponse> response = pageCategory
                .map(category -> new CategorySummaryResponse(category.getId(), category.getDescription()));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<CategoryDetailedResponse> findCategoriesById(UUID categoryId) {
        Category category = categoryService.findCategoryById(categoryId);

//        CategorySummaryResponse categorySummary = new CategorySummaryResponse(
//                category.getId(),
//                category.getDescription()
//        );

        CategoryDetailedResponse response = new CategoryDetailedResponse(
                category.getId(),
                category.getRatings(),
                category.getDescription(),
                category.getImage_url()
        );

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> createCategory(@RequestBody @Valid CreateCategoryRequest request) {

        Category category = Category.newCategory(
                request.ratings(),
                request.description(),
                request.image_url()
        );

        Category createdCategory = categoryService.createCategory(category);
        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(createdCategory.getId()).toUri()).build();
    }

    @Override
    public ResponseEntity<Void> updateCategoryById(UUID categoryId, Map<String, Object> fields) {
        categoryService.updateCategoryById(categoryId,fields);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteCategoryById(UUID categoryId) {
        categoryService.deleteCategoryById(categoryId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Page<HotelSummaryResponse>> findHotelsByCategoryId(UUID categoryId, Pageable pageable) {
        Page<Hotel> hotelsPage = hotelService.findHotelsByCategoryId(categoryId, pageable);
        Page<HotelSummaryResponse> response = hotelsPage.map(hotel ->
                new HotelSummaryResponse(
                        hotel.getId(),
                        hotel.getTrading_name(),
                        hotel.getCnpj(),
                        hotel.getDescription()
                ));
        return ResponseEntity.ok(response);
    }

}
