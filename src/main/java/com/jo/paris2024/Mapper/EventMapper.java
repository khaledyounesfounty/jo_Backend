package com.jo.paris2024.Mapper;

import com.jo.paris2024.DTO.EventDto;
import com.jo.paris2024.entities.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {
     EventDto toDto(Event event);

    public Event toEntity(EventDto eventDto);
}
