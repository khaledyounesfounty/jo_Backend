package com.jo.paris2024.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "qrcode")
public class Qrcode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_qr", nullable = false)
    private Integer id;

    @Column(name = "data")
    private String data;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "billet_id")
    private Billet billet;

    @Column(name = "qr_image")
    private String qrImage;

}