package com.br.digital_hoteis.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "characteristics")
public class Characteristics {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String icon;

    @ElementCollection
    @CollectionTable(name = "hotel_characteristics", joinColumns = @JoinColumn(name = "hotel_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "facility")
    @Column(name = "value")
    private Map<Facilities, Boolean> facilities;

    @ManyToMany(mappedBy = "characteristics")
    private Set<Hotel> hotels = new HashSet<>();

    @Override
    public String toString() {
        return "Characteristics{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }

    public static Characteristics newCharacteristics(
            String name,
            String icon) {
        Characteristics characteristics = new Characteristics();
        characteristics.setName(name);
        characteristics.setIcon(icon);


        Map<Facilities, Boolean> defaultFacilities = new HashMap<>();
        // o false ali deixa todas as facilidades como falso
        Arrays.stream(Facilities.values()).forEach(facility -> defaultFacilities.put(facility, false));
        characteristics.setFacilities(defaultFacilities);

        return characteristics;
    }

}
