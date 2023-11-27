package com.br.digital_hoteis.app.runner;

import com.br.digital_hoteis.domain.entity.Category;
import com.br.digital_hoteis.domain.entity.RatingEnum;
import com.br.digital_hoteis.domain.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Profile("dev")
@Component
public class PopulateCategoryRunner implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    @Autowired
    public PopulateCategoryRunner(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    private Category createCategoryWithDetails(RatingEnum ratings, String description, String imageUrl) {
        Category category = new Category();
        category.setRatings(ratings);
        category.setDescription(description);
        category.setImage_url(imageUrl);
        return category;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        List<Category> categories = new ArrayList<>();

        // Hotel Category
        Category hotels = createCategoryWithDetails(
                RatingEnum.GOOD,
                "A five-star hotel perfect for a family's vacation",
                "https://images.unsplash.com/photo-1455587734955-081b22074882"
        );

        Category hotels_two = createCategoryWithDetails(
                RatingEnum.AVERAGE,
                "A five-star hotel perfect for a family's vacation",
                "https://images.unsplash.com/photo-1566073771259-6a8506099945"
        );
        categories.add(hotels);
        categories.add(hotels_two);

        // Hostel Category
        Category hostels = createCategoryWithDetails(
                RatingEnum.EXCELLENT,
                "A three-star hostel comfortable enough for short trips",
                "https://images.unsplash.com/photo-1555854877-bab0e564b8d5"
        );
        categories.add(hostels);

        Category hostels_two = createCategoryWithDetails(
                RatingEnum.BAD,
                "A three-star hostel comfortable enough for short trips",
                "https://images.unsplash.com/photo-1629794226066-349748040fb7"
        );
        categories.add(hostels_two);

        // Apartments Category
        Category apartments = createCategoryWithDetails(
                RatingEnum.VERY_GOOD,
                "Perfect place to feel at home on trips",
                "https://images.unsplash.com/photo-1551361415-69c87624334f"
        );
        categories.add(apartments);

        Category apartments_two = createCategoryWithDetails(
                RatingEnum.AVERAGE,
                "Perfect place to feel at home on trips and family events",
                "https://images.unsplash.com/photo-1619994121345-b61cd610c5a6"
        );
        categories.add(apartments_two);

        // Bed & Breakfast Category
        Category bedAndBreakfast = createCategoryWithDetails(
                RatingEnum.BAD,
                "Economic and comfortable option for long trips",
                "https://images.unsplash.com/photo-1613618948931-efbc3e6f9775"
        );
        categories.add(bedAndBreakfast);

        Category bedAndBreakfast_two = createCategoryWithDetails(
                RatingEnum.GOOD,
                "Economic and comfortable option for long trips",
                "https://images.unsplash.com/photo-1597776776684-61122dcf84e6"
        );
        categories.add(bedAndBreakfast_two);

        categoryRepository.saveAll(categories);
    }
}

