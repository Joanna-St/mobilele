package org.softuni.mobilele.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Getter
@Setter
@NoArgsConstructor
@Component("currentUser")
@SessionScope
public class CurrentUser {
    private String firstName;
    private String lastName;
    private String role;
    private boolean isLogged;

    public void logout(){
        setFirstName(null);
        setLastName(null);
        setRole(null);
        setLogged(false);
    }
    @Override
    public String toString() {
        return "CurrentUser{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role='" + role + '\'' +
                ", isLogged=" + isLogged +
                '}';
    }
}
