package com.jo.paris2024.entities;

import com.fasterxml.jackson.databind.DatabindException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="event")
public class Event {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
@NotNull
@NotBlank
@Lob
@Column(name = "Titre", nullable = false)
    private String Titre;
    private  Date   Date;
    private String Lieu;
    private String Description;
    private Integer NombreDePlacesMax;
    private Integer NombreDePlacesDisponibles;
    private Boolean IsDisponible;
}
