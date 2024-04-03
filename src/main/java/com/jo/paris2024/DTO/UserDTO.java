package com.jo.paris2024.DTO;
import com.jo.paris2024.entities.Utilisateurprincipal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserDTO {
    private long id;
    private String username;
    private String role;
    private String prenom ;



    public UserDTO(Utilisateurprincipal actualUser) {
        this.id = actualUser.getId();
        this.username = actualUser.getEmail();
        this.role = actualUser.getRole();
        this.prenom = actualUser.getPrenom();
    }
}
