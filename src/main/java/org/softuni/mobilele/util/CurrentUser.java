package org.softuni.mobilele.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.softuni.mobilele.models.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Getter
@Setter
@NoArgsConstructor
@Component("currentUser")
@SessionScope
public class CurrentUser {
    private Long id;
    private String firstName;
    private String lastName;
    private String role;
    private boolean isLogged;

    public void logout() {
        setId(null);
        setFirstName(null);
        setLastName(null);
        setRole(null);
        setLogged(false);
    }

    public void login(User user) {
        setId(user.getId());
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
        setRole(user.getRole().getRole().name());
        setLogged(true);
    }
}
