package org.softuni.mobilele.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.softuni.mobilele.models.enums.EngineEnum;
import org.softuni.mobilele.models.enums.TransmissionEnum;

import java.math.BigDecimal;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "offers")
public class Offer extends BaseEntity {
    @Column(nullable = false)
    @JdbcTypeCode(Types.VARCHAR)
    private UUID uuid;

    @Column(columnDefinition = "text")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EngineEnum engine;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(nullable = false)
    private Integer mileage;

    @Column(nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransmissionEnum transmission;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private LocalDateTime created;

    @Column
    private LocalDateTime modified;

    @ManyToOne
    private Model model;

    @ManyToOne
    private User seller;
}
