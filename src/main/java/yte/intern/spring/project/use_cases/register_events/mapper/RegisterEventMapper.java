package yte.intern.spring.project.use_cases.register_events.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import yte.intern.spring.project.use_cases.common.dto.RegisterEventDTO;
import yte.intern.spring.project.use_cases.common.entity.Event;

@Mapper(componentModel = "spring")
public interface RegisterEventMapper {
    
    RegisterEventDTO mapToDto(Event event);

    List<RegisterEventDTO> mapToDto(List<Event> eventList);

    Event mapToEntity(RegisterEventDTO eventDTO);

    List<Event> mapToEntity(List<RegisterEventDTO> eventDTOList);
}