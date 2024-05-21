package com.jo.paris2024.controller;

import com.jo.paris2024.DTO.OffreDto;
import com.jo.paris2024.controllers.OffreController;
import com.jo.paris2024.entities.Offre;
import com.jo.paris2024.services.OffreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OffreControllerTest {

    @InjectMocks
    private OffreController offreController;

    @Mock
    private OffreService offreService;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllOffresReturnsListOfOffres() {
        Offre expectedOffre = new Offre();
        when(offreService.getAllOffres()).thenReturn(Arrays.asList(expectedOffre));

        ResponseEntity<?> response = offreController.getAllOffre();

        assertEquals(ResponseEntity.ok(Arrays.asList(expectedOffre)), response);
    }

    @Test
    public void getOffreByIdReturnsOffre() {
        Offre expectedOffre = new Offre();
        when(offreService.getOffreById(anyInt())).thenReturn(expectedOffre);

        ResponseEntity<?> response = offreController.getOffreById(1);

        assertEquals(ResponseEntity.ok(expectedOffre), response);
    }

    @Test
    public void saveOffreReturnsBadRequestWhenBindingResultHasErrors() {
        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<?> response = offreController.saveOffre(new OffreDto(), bindingResult);

        assertEquals(ResponseEntity.badRequest().body(bindingResult.getAllErrors()), response);
    }

    @Test
    public void saveOffreReturnsOkWhenOffreIsSaved() {
        when(bindingResult.hasErrors()).thenReturn(false);

        ResponseEntity<?> response = offreController.saveOffre(new OffreDto(), bindingResult);

        assertEquals(ResponseEntity.ok("L'offre a été ajoutée avec succes"), response);
    }

    @Test
    public void updateOffreReturnsOkWhenOffreIsUpdated() {
        ResponseEntity<?> response = offreController.updateOffre(1, new OffreDto(), bindingResult);

        assertEquals(ResponseEntity.ok("L'offre a été mise à jour avec succes"), response);
    }

    @Test
    public void deleteOffreReturnsOkWhenOffreIsDeleted() {
        ResponseEntity<String> response = offreController.deleteOffre(1);

        assertEquals(ResponseEntity.ok("L'offre a été supprimée avec succes"), response);
    }
}