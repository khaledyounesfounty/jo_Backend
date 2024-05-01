package com.jo.paris2024.Mapper;

import com.jo.paris2024.DTO.EventDto;
import com.jo.paris2024.entities.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapper {
    @Mapping(target = "offresIds", ignore = true)
     EventDto toDto(Event event);


    Event toEntity(EventDto eventDto);
}
