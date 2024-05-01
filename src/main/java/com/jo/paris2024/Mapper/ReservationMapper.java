package com.jo.paris2024.Mapper;

import com.jo.paris2024.DTO.ReservationDto;
import com.jo.paris2024.entities.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    Reservation toEntity(ReservationDto reservationDto);
    ReservationDto toDto(Reservation reservation);

}
