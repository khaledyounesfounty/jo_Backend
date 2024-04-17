package com.jo.paris2024.services.impl;

import com.jo.paris2024.DTO.ReservationDto;
import com.jo.paris2024.Mapper.ReservationMapper;
import com.jo.paris2024.entities.*;
import com.jo.paris2024.repository.*;
import com.jo.paris2024.services.PanierService;
import com.jo.paris2024.services.ReservationService;
import com.jo.paris2024.services.UtilisateurprincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final OffreRepository offreRepository;
    private final EventRepository eventRepository;
    private final ReservationMapper reservationMapper;
    private final UtilisateurprincipalService utilisateurprincipalService;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, UtilisateurprincipalRepository utilisateurprincipalRepository, OffreRepository offreRepository, EventRepository eventRepository, ReservationMapper reservationMapper, UtilisateurprincipalService utilisateurprincipalService) {
        this.reservationRepository = reservationRepository;
        this.offreRepository = offreRepository;
        this.eventRepository = eventRepository;
        this.reservationMapper = reservationMapper;
        this.utilisateurprincipalService = utilisateurprincipalService;
    }

    @Override
    public void saveReservation(ReservationDto reservationDto) {

        Reservation reservation = reservationMapper.toEntity(reservationDto);
        Utilisateur utilisateur = utilisateurprincipalService.getUtilisateurLogin();
        reservation.setIdPanier(utilisateur.getPanier());
        Offre offre = offreRepository.findById(reservationDto.getIdOffreId()).orElseThrow(() -> new IllegalArgumentException("Offre not found"));
        reservation.setIdOffre(offre);
        Event event = eventRepository.findById(reservationDto.getIdEventId().intValue()).orElseThrow(() -> new IllegalArgumentException("Event not found"));
        reservation.setIdEvent(event);

        double prixtotal = event.getPrixUnitaire()* offre.getNbPlace() *( 1 - ((double) offre.getRemise() / 100) )  ;
        reservation.setPrix(prixtotal);
        reservationRepository.save(reservation);
        reservation.getIdPanier().getReservations().add(reservation);

    }



    @Override
    public Reservation getReservationById(Integer idReservation) {
        return reservationRepository.findById(idReservation).orElseThrow(() -> new IllegalArgumentException("La reservation n'existe pas"));
    }
}
