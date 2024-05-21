package com.jo.paris2024.controller;

import com.jo.paris2024.DTO.BilletDto;
import com.jo.paris2024.Mapper.BilletMapper;
import com.jo.paris2024.controllers.BilletController;
import com.jo.paris2024.entities.Billet;
import com.jo.paris2024.services.BilletService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BilletControllerTest {

    @InjectMocks
    private BilletController billetController;

    @Mock
    private BilletService billetService;
    @Mock
    private BilletMapper billetMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getBilletsReturnsListOfBillets() {
        Billet billet = new Billet();
        BilletDto expectedBilletDto = new BilletDto();
        when(billetService.getAllBillets()).thenReturn(Arrays.asList(billet));
        when(billetMapper.toBilletDto(billet)).thenReturn(expectedBilletDto);

        ResponseEntity<?> response = billetController.getBillets();

        assertEquals(ResponseEntity.ok(Arrays.asList(expectedBilletDto)), response);
    }

    @Test
    public void getBilletsReturnsEmptyListWhenNoBillets() {
        when(billetService.getAllBillets()).thenReturn(Collections.emptyList());
        when(billetMapper.toBilletDto(any(Billet.class))).thenReturn(new BilletDto());

        ResponseEntity<?> response = billetController.getBillets();

        assertEquals(ResponseEntity.ok(Collections.emptyList()), response);
    }
}