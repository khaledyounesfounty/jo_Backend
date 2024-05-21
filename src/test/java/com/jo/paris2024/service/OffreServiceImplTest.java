package com.jo.paris2024.service;

import com.jo.paris2024.DTO.OffreDto;
import com.jo.paris2024.entities.Offre;
import com.jo.paris2024.repository.OffreRepository;
import com.jo.paris2024.Mapper.OffreMapper;
import com.jo.paris2024.services.impl.OffreServiceImpl;
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

public class OffreServiceImplTest {

    @InjectMocks
    private OffreServiceImpl offreService;

    @Mock
    private OffreRepository offreRepository;

    @Mock
    private OffreMapper offreMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllOffresReturnsListOfOffres() {
        when(offreRepository.findAll()).thenReturn(Arrays.asList(new Offre()));

        assertEquals(1, offreService.getAllOffres().size());
    }

    @Test
    public void getAllOffresThrowsExceptionWhenNoOffres() {
        when(offreRepository.findAll()).thenReturn(Arrays.asList());

        assertThrows(IllegalArgumentException.class, () -> offreService.getAllOffres());
    }

    @Test
    public void getOffreByIdReturnsOffre() {
        Offre expectedOffre = new Offre();
        when(offreRepository.findById(1)).thenReturn(Optional.of(expectedOffre));

        Offre actualOffre = offreService.getOffreById(1);
        assertEquals(expectedOffre, actualOffre);
    }

    @Test
    public void getOffreByIdThrowsExceptionWhenNoOffre() {
        when(offreRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> offreService.getOffreById(1));
    }

    @Test
    public void saveOffreSavesOffre() {
        OffreDto offreDto = new OffreDto();
        Offre offre = new Offre();
        when(offreRepository.findByTitre(offreDto.getTitre())).thenReturn(Arrays.asList());
        when(offreMapper.toEntity(offreDto)).thenReturn(offre);

        offreService.saveOffre(offreDto);

        verify(offreRepository).save(offre);
    }

    @Test
    public void saveOffreThrowsExceptionWhenOffreExists() {
        OffreDto offreDto = new OffreDto();
        when(offreRepository.findByTitre(offreDto.getTitre())).thenReturn(Arrays.asList(new Offre()));

        assertThrows(IllegalArgumentException.class, () -> offreService.saveOffre(offreDto));
    }
}