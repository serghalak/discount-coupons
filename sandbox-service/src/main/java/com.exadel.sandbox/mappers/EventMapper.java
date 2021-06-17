package com.exadel.sandbox.mappers;

import com.exadel.sandbox.model.vendorinfo.Event;
import com.exadel.sandbox.service.vendor.dto.EventDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@AllArgsConstructor
@Component
public class EventMapper {
    private ModelMapper mapper;

    public Event toEntity(EventDto dto){
        return Objects.isNull(dto) ? null : mapper.map(dto, Event.class);

    }

    public EventDto toDto(Event event){
        return Objects.isNull(event) ? null : mapper.map(event, EventDto.class);
    }
}
