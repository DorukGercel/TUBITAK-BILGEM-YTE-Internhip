package yte.intern.spring.project.use_cases.manage_events.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import yte.intern.spring.project.use_cases.common.dto.AddEventDTO;
import yte.intern.spring.project.use_cases.common.entity.Event;

@Mapper(componentModel = "spring")
public interface AddEventMapper {
    
    AddEventDTO mapToDto(Event event);

    List<AddEventDTO> mapToDto(List<Event> eventList);

    Event mapToEntity(AddEventDTO eventDTO);

    List<Event> mapToEntity(List<AddEventDTO> eventDTOList);
}