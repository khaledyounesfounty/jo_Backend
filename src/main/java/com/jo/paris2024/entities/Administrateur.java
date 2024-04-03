package com.jo.paris2024.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "administrateur")
public class Administrateur {
    @Id
    @Column(name = "idAdministrateur", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idAdministrateur", nullable = false)
    private Utilisateurprincipal utilisateurprincipal;

}