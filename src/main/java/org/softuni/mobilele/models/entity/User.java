package org.softuni.mobilele.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "is_active", columnDefinition = "bit")
    private boolean isActive;
    @ManyToOne
    private UserRole role;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(nullable = false)
    private LocalDateTime created;
    @Column
    private LocalDateTime modified;
}