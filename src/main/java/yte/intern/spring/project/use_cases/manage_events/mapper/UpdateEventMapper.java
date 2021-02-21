package yte.intern.spring.project.use_cases.manage_events.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import yte.intern.spring.project.use_cases.common.dto.UpdateEventDTO;
import yte.intern.spring.project.use_cases.common.entity.Event;

@Mapper(componentModel = "spring")
public interface UpdateEventMapper {
    
    UpdateEventDTO mapToDto(Event event);

    List<UpdateEventDTO> mapToDto(List<Event> eventList);

    Event mapToEntity(UpdateEventDTO eventDTO);

    List<Event> mapToEntity(List<UpdateEventDTO> eventDTOList);
}