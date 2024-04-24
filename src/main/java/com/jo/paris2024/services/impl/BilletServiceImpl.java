package com.jo.paris2024.services.impl;

import com.jo.paris2024.Mapper.BilletMapper;
import com.jo.paris2024.entities.Billet;
import com.jo.paris2024.entities.Qrcode;
import com.jo.paris2024.entities.Reservation;
import com.jo.paris2024.repository.BilletRepository;
import com.jo.paris2024.services.BilletService;
import com.jo.paris2024.services.EmailService;
import com.jo.paris2024.services.QrcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class BilletServiceImpl implements BilletService {

    @Autowired
    private QrcodeService qrcodeService;
    @Autowired
    private BilletRepository billetRepository;
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
        sendBilletEmail(newBillet);
        return newBillet;
    }

    @Override
    public void sendBilletEmail(Billet billet) {
        emailService.sendBilletConfirmationEmail(billetMapper.toBilletDto(billet), billet.getIdUtilisateur().getUtilisateurprincipal().getEmail());
    }

}
