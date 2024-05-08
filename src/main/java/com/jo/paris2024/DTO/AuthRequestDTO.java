package com.jo.paris2024.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class AuthRequestDTO {
    private String username;
    private String password;
}