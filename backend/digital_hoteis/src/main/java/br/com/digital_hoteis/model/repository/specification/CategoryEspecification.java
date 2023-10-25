package br.com.digital_hoteis.model.repository.specification;

import br.com.digital_hoteis.model.entity.Category;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class CategoryEspecification {

    private CategoryEspecification() {}

    public static Specification<Category> byHotelId(UUID id) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("hotel").get("id"), id));
    }

}
