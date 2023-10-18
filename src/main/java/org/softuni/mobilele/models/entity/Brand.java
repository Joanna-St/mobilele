package org.softuni.mobilele.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@NamedEntityGraph(name = "brandsAndModels", attributeNodes = @NamedAttributeNode("models"))
@Table(name = "brands")
public class Brand extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime created;

    @Column
    private LocalDateTime modified;

    @OneToMany(targetEntity = Model.class, mappedBy = "brand")
    private List<Model> models;
}
