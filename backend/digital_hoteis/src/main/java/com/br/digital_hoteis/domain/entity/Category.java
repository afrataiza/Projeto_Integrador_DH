package com.br.digital_hoteis.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "category")
@EntityListeners(AuditingEntityListener.class)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "ratings")
    private RatingEnum ratings;

    @Column(nullable = false)
    private String description;

    private String image_url;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Hotel> hotels = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category category)) return false;
        return Objects.equals(getId(),
                category.getId())
                && Objects.equals(getRatings(), category.getRatings())
                && Objects.equals(getDescription(), category.getDescription())
                && Objects.equals(getImage_url(), category.getImage_url());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getRatings(),
                getDescription(),
                getImage_url());
    }

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
            RatingEnum ratings,
            String description,
            String image_url) {
        Category category = new Category();
        category.setRatings(ratings);
        category.setDescription(description);
        category.setImage_url(image_url);
        return category;
    }

    public void addHotel(Hotel hotel) {
        hotels.add(hotel);
        hotel.setCategory(this);
    }

    public void removeHotel(Hotel hotel) {
        hotels.remove(hotel);
        hotel.setCategory(null);
    }
}

