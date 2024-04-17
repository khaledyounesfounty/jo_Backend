package com.jo.paris2024.services.impl;

import com.jo.paris2024.entities.Billet;
import com.jo.paris2024.entities.Qrcode;
import com.jo.paris2024.entities.Reservation;
import com.jo.paris2024.repository.BilletRepository;
import com.jo.paris2024.services.BilletService;
import com.jo.paris2024.services.QrcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BilletServiceImpl implements BilletService {

    @Autowired
    private QrcodeService qrcodeService;
    @Autowired
    private BilletRepository billetRepository;
    @Override
    public Billet creerBillet(Reservation reservation) {
        Billet billet = new Billet();
        billet.setReservation(reservation);
        billet.setIdUtilisateur(reservation.getIdPanier().getUtilisateur());
        billet.setCleBillet(UtilisateurprincipalServiceimpl.generateCle());
        Qrcode qrcode = qrcodeService.creerQrcode(billet.getCleBillet()+reservation.getIdPanier().getUtilisateur().getCleUtilisateur());
        billet.setQrcode(qrcode);
        billetRepository.save(billet);
        return billet;
    }

    // send the billets to the utilisateur using his gmail

}
