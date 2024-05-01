package com.jo.paris2024.Mapper;

import com.jo.paris2024.entities.Billet;
import com.jo.paris2024.DTO.BilletDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BilletMapper {
    BilletDto toBilletDto(Billet billet);

    Billet toBillet(BilletDto billetDto);
}
