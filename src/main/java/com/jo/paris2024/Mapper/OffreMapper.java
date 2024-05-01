package com.jo.paris2024.Mapper;

import com.jo.paris2024.DTO.OffreDto;
import com.jo.paris2024.entities.Offre;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OffreMapper {
    OffreDto toDto(Offre offre);
    Offre toEntity(OffreDto offreDto);
}
