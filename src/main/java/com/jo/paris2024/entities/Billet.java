package com.jo.paris2024.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "billet")
public class Billet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idbillet", nullable = false)
    private Integer id;

    @Size(max = 100)
    @Column(name = "type", length = 100)
    private String type;

    @NotNull
    @Column(name = "validite", nullable = false)
    private Boolean validite = false;

    @Size(max = 255)
    @Column(name = "cleBillet")
    private String cleBillet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offreId")
    private Offre offre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUtilisateur")
    private Utilisateurprincipal idUtilisateur;

    @OneToOne(mappedBy = "billet")
    private Qrcode qrcode;

}