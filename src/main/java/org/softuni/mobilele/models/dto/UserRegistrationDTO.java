package org.softuni.mobilele.models.dto;

import org.softuni.mobilele.models.entity.UserRole;

import java.time.LocalDateTime;

public record UserRegistrationDTO(String firstName,
                                  String lastName,
                                  String username,
                                  String password,
                                  LocalDateTime created) {
}
