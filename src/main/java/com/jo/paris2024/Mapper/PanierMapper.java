package com.jo.paris2024.Mapper;

import com.jo.paris2024.DTO.PanierDto;
import com.jo.paris2024.entities.Panier;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PanierMapper {
    PanierDto toPanierDto(Panier panier);
    Panier toPanier(PanierDto panierDto);
}
