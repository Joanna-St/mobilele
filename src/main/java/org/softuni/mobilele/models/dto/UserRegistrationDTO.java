package org.softuni.mobilele.models.dto;

import java.time.LocalDateTime;

public record UserRegistrationDTO(String firstName,
                                  String lastName,
                                  String username,
                                  String password,
                                  String role,
                                  LocalDateTime created) {
}
