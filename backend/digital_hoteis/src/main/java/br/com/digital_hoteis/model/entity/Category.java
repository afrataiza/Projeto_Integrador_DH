package br.com.digital_hoteis.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

//    private RatingEnum rating;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    @CollectionTable(name = "ratings", joinColumns = @JoinColumn(name = "category_id"))
    @Column(name = "ratings", columnDefinition = "VARCHAR(50)")
    private Set<RatingEnum> ratings;

    @Column(nullable = false)
    private String description;

    private String image_url;

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", ratings=" + ratings +
                ", description='" + description + '\'' +
                ", image_url='" + image_url + '\'' +
                '}';
    }

    public static Category newCategory(
            Set<RatingEnum> ratings,
            String description,
            String image_url) {
        Category category = new Category();
        category.setRatings(ratings);
        category.setDescription(description);
        category.setImage_url(image_url);
        return category;
    }
}
