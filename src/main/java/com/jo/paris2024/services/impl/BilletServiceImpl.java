package com.jo.paris2024.services.impl;

import com.jo.paris2024.Mapper.BilletMapper;
import com.jo.paris2024.entities.Billet;
import com.jo.paris2024.entities.Qrcode;
import com.jo.paris2024.entities.Reservation;
import com.jo.paris2024.entities.Utilisateur;
import com.jo.paris2024.repository.BilletRepository;
import com.jo.paris2024.repository.ReservationRepository;
import com.jo.paris2024.services.BilletService;
import com.jo.paris2024.services.EmailService;
import com.jo.paris2024.services.QrcodeService;
import com.jo.paris2024.services.UtilisateurprincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class BilletServiceImpl implements BilletService {

    @Autowired
    private QrcodeService qrcodeService;
    @Autowired
    private UtilisateurprincipalService utilisateurprincipalService;
    @Autowired
    private BilletRepository billetRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    EmailService emailService;
    Logger logger = Logger.getLogger(BilletServiceImpl.class.getName());
    @Autowired
    private BilletMapper billetMapper;

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
    public void sendBilletEmail(Billet billet) {
        emailService.sendBilletConfirmationEmail(billetMapper.toBilletDto(billet), billet.getIdUtilisateur().getUtilisateurprincipal().getEmail());
    }

    @Override
    public List<Billet> getAllBillets() {
        Utilisateur utilisateur = utilisateurprincipalService.getUtilisateurLogin();
        return utilisateur.getBillets();
    }

}
