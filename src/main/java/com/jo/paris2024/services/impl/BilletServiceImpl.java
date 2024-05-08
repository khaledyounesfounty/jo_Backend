package com.jo.paris2024.services.impl;

import com.jo.paris2024.Mapper.BilletMapper;
import com.jo.paris2024.entities.Billet;
import com.jo.paris2024.entities.Qrcode;
import com.jo.paris2024.entities.Reservation;
import com.jo.paris2024.entities.Utilisateur;
import com.jo.paris2024.repository.BilletRepository;
import com.jo.paris2024.repository.ReservationRepository;
import com.jo.paris2024.services.BilletService;
import com.jo.paris2024.services.QrcodeService;
import com.jo.paris2024.services.UtilisateurprincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class BilletServiceImpl implements BilletService {

    private final QrcodeService qrcodeService;
    private final UtilisateurprincipalService utilisateurprincipalService;
    private final BilletRepository billetRepository;
    private final ReservationRepository reservationRepository;
    Logger logger = Logger.getLogger(BilletServiceImpl.class.getName());
    private final BilletMapper billetMapper;
    @Autowired
    public BilletServiceImpl( QrcodeService qrcodeService, UtilisateurprincipalService utilisateurprincipalService, BilletRepository billetRepository, ReservationRepository reservationRepository, BilletMapper billetMapper) {
        this.qrcodeService = qrcodeService;
        this.utilisateurprincipalService = utilisateurprincipalService;
        this.billetRepository = billetRepository;
        this.reservationRepository = reservationRepository;
        this.billetMapper = billetMapper;
    }

    @Override
    public Billet creerBillet(Reservation reservation) {
        Billet billet = new Billet();
        billet.setReservation(reservation);
        billet.setIdUtilisateur(reservation.getIdPanier().getUtilisateur());
        billet.setCleBillet(UtilisateurprincipalServiceimpl.generateCle());

        logger.info("Billet created " + billet);
        Billet newBillet = billetRepository.save(billet);
        Qrcode qrcode = qrcodeService.createAndSaveQRCode(billet.getCleBillet() + reservation.getIdPanier().getUtilisateur().getCleUtilisateur(), newBillet);
        newBillet.setQrcode(qrcode);
        // set reservation panier to null
        reservation.setIdPanier(null);
        reservationRepository.save(reservation);
        //sendBilletEmail(newBillet);
        return newBillet;
    }

    @Override
    public List<Billet> getAllBillets() {
        Utilisateur utilisateur = utilisateurprincipalService.getUtilisateurLogin();
        return utilisateur.getBillets();
    }

}
