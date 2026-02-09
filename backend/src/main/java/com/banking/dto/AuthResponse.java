package com.banking.dto;

import com.banking.entity.User;
import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class AuthResponse {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
    private String message;
    
    public static AuthResponse fromUser(User user, String message) {
        return new AuthResponse(
            user.getId(),
            user.getEmail(),
            user.getFirstName(),
            user.getLastName(),
            user.getRole().name(),
            message
        );
    }
}
