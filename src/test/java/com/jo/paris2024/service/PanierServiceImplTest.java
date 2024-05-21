package com.jo.paris2024.service;

import com.jo.paris2024.DTO.PanierDto;
import com.jo.paris2024.entities.Panier;
import com.jo.paris2024.entities.Reservation;
import com.jo.paris2024.entities.Utilisateur;
import com.jo.paris2024.repository.PanierRepository;
import com.jo.paris2024.services.BilletService;
import com.jo.paris2024.services.ReservationService;
import com.jo.paris2024.services.UtilisateurprincipalService;
import com.jo.paris2024.services.impl.PanierServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PanierServiceImplTest {

    @InjectMocks
    private PanierServiceImpl panierService;

    @Mock
    private PanierRepository panierRepository;

    @Mock
    private ReservationService reservationService;
    @Mock
    private BilletService billetService;

    @Mock
    private UtilisateurprincipalService utilisateurprincipalService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createPanierSavesPanier() {
        Panier panier = new Panier();
        when(panierRepository.save(panier)).thenReturn(panier);

        assertEquals(panier, panierService.createPanier(panier));
    }

    @Test
    public void validerPanierClearsReservations() {
        Panier panier = new Panier();
        panier.getReservations().add(new Reservation());
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setPanier(panier);
        when(utilisateurprincipalService.getUtilisateurLogin()).thenReturn(utilisateur);

        panierService.validerPanier();

        verify(panierRepository).save(panier);
        assertEquals(0, panier.getReservations().size());
    }
}