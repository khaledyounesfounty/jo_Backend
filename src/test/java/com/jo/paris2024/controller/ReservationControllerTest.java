package com.jo.paris2024.controller;

import com.jo.paris2024.DTO.ReservationDto;
import com.jo.paris2024.controllers.ReservationController;
import com.jo.paris2024.services.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ReservationControllerTest {

    @InjectMocks
    private ReservationController reservationController;

    @Mock
    private ReservationService reservationService;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveReservationReturnsSuccessWhenNoErrors() {
        ReservationDto reservationDto = new ReservationDto();
        when(bindingResult.hasErrors()).thenReturn(false);

        ResponseEntity<?> response = reservationController.saveReservation(reservationDto, bindingResult);

        assertEquals(ResponseEntity.ok("La reservation a été ajoutée avec succes"), response);
        verify(reservationService).saveReservation(reservationDto);
    }

    @Test
    public void saveReservationReturnsBadRequestWhenErrors() {
        ReservationDto reservationDto = new ReservationDto();
        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<?> response = reservationController.saveReservation(reservationDto, bindingResult);

        assertEquals(ResponseEntity.badRequest().body("Les champs ne sont pas valides"), response);
        verify(reservationService, never()).saveReservation(reservationDto);
    }
}