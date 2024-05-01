package com.jo.paris2024.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name = "billet")
public class Billet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idbillet", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "validite", nullable = false)
    private Boolean validite = true;

    @Size(max = 255)
    @Column(name = "cle_billet")
    private String cleBillet;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reservation")
    @ToString.Exclude
    private Reservation reservation;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_utilisateur")
    @ToString.Exclude
    private Utilisateur idUtilisateur;

    @OneToOne(mappedBy = "billet")
    private Qrcode qrcode;

}