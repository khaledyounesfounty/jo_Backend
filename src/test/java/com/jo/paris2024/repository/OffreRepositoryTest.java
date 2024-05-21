package com.jo.paris2024.repository;

import com.jo.paris2024.entities.Offre;
import com.jo.paris2024.repository.OffreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class OffreRepositoryTest {

    @Mock
    private OffreRepository offreRepository;

    @Mock
    private Offre offre;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findByTitreReturnsOffreList() {
        when(offreRepository.findByTitre("title")).thenReturn(Arrays.asList(offre));

        List<Offre> offres = offreRepository.findByTitre("title");

        assertEquals(1, offres.size());
    }
}