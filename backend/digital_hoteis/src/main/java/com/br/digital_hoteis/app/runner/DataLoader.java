package com.br.digital_hoteis.app.runner;

import com.br.digital_hoteis.domain.entity.Category;
import com.br.digital_hoteis.domain.entity.RatingEnum;
import com.br.digital_hoteis.domain.repository.CategoryRepository;
import com.br.digital_hoteis.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public DataLoader(UserRepository userRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;

    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordAdmin = passwordEncoder.encode("passwordAdmin");
        String passwordUser = passwordEncoder.encode("passwordUser");

        // Hotel Category
        Category hotels = createCategoryWithDetails(
                RatingEnum.EXCELLENT,
                "A five-star hotel perfect for a family's vacation",
                "https://images.unsplash.com/photo-1455587734955-081b22074882"
        );
        Category hotels_two = createCategoryWithDetails(
                RatingEnum.AVERAGE,
                "A five-star hotel perfect for a family's vacation",
                "https://images.unsplash.com/photo-1566073771259-6a8506099945"
        );

        // Hostel Category
        Category hostels = createCategoryWithDetails(
                RatingEnum.BAD,
                "A three-star hostel comfortable enough for short trips",
                "https://images.unsplash.com/photo-1555854877-bab0e564b8d5"
        );


        Category hostels_two = createCategoryWithDetails(
                RatingEnum.VERY_GOOD,
                "A three-star hostel comfortable enough for short trips",
                "https://images.unsplash.com/photo-1629794226066-349748040fb7"
        );


        // Apartments Category
        Category apartments = createCategoryWithDetails(
                RatingEnum.AVERAGE,
                "Perfect place to feel at home on trips",
                "https://images.unsplash.com/photo-1551361415-69c87624334f"
        );

        Category apartments_two = createCategoryWithDetails(
                RatingEnum.GOOD,
                "Perfect place to feel at home on trips and family events",
                "https://images.unsplash.com/photo-1619994121345-b61cd610c5a6"
        );

        // Bed & Breakfast Category
        Category bedAndBreakfast = createCategoryWithDetails(
                RatingEnum.EXCELLENT,
                "Economic and comfortable option for long trips",
                "https://images.unsplash.com/photo-1613618948931-efbc3e6f9775"
        );

        Category bedAndBreakfast_two = createCategoryWithDetails(
                RatingEnum.AVERAGE,
                "Economic and comfortable option for long trips",
                "https://images.unsplash.com/photo-1597776776684-61122dcf84e6"
        );


        List<Category> categories = new ArrayList<>();
        categories.add(hotels);
        categories.add(hotels_two);
        categories.add(hostels);
        categories.add(hostels_two);
        categories.add(apartments);
        categories.add(apartments_two);
        categories.add(bedAndBreakfast);
        categories.add(bedAndBreakfast_two);

        categoryRepository.saveAll(categories);

//        userRepository.save(
//                new UserDetail(
//                        "John",
//                        "Doe",
//                        "admin3@digitalhoteis.fake.com",
//                        passwordAdmin,
//                        true,
//                        true
//                                ));
//
//        userRepository.save(
//                new UserDetail(
//                        "Regular",
//                        "User",
//                        "john2@digitalhoteis.fake.com",
//                        passwordUser,
//                        false,
//                        true
//                                ));
    }

    private Category createCategoryWithDetails(RatingEnum ratings, String description, String imageUrl) {
        Category category = new Category();
        category.setRatings(ratings);
        category.setDescription(description);
        category.setImage_url(imageUrl);
        return category;
    }



}
