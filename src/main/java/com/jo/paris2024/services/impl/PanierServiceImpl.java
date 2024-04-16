package com.jo.paris2024.services.impl;

import com.jo.paris2024.DTO.PanierDto;
import com.jo.paris2024.Mapper.PanierMapper;
import com.jo.paris2024.entities.Panier;
import com.jo.paris2024.entities.Reservation;
import com.jo.paris2024.entities.Utilisateur;
import com.jo.paris2024.repository.PanierRepository;
import com.jo.paris2024.services.PanierService;
import com.jo.paris2024.services.ReservationService;
import com.jo.paris2024.services.UtilisateurprincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PanierServiceImpl implements PanierService {

    @Autowired
    private PanierRepository panierRepository;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private UtilisateurprincipalService utilisateurprincipalService;
    @Autowired
    private PanierMapper panierMapper;

    @Override
    public Panier createPanier(Panier panier) {
        return panierRepository.save(panier);
    }

    @Override
    public void deleteReservationFromPanier(Integer idReservation) {
        Reservation reservation = reservationService.getReservationById(idReservation);
        Panier panier = utilisateurprincipalService.getUtilisateurLogin().getPanier();
        panier.getReservations().remove(reservation);
        panier.setSommmeTotal(panier.getSommmeTotal()-reservation.getPrix());
        panierRepository.save(panier);
    }

    @Override
    public void addReservationToPanier(Reservation reservation, Panier panier) {
        panier.getReservations().add(reservation);
        panier.setSommmeTotal(panier.getSommmeTotal()+reservation.getPrix());
        panierRepository.save(panier);
    }

    @Override
    public PanierDto getPanierDetails() {
        Panier panier = utilisateurprincipalService.getUtilisateurLogin().getPanier();
        return panierMapper.toPanierDto(panier);
    }


}
