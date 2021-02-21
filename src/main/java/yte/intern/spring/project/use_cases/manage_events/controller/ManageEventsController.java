package yte.intern.spring.project.use_cases.manage_events.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import yte.intern.spring.project.use_cases.common.dto.AddEventDTO;
import yte.intern.spring.project.use_cases.common.dto.EventDTO;
import yte.intern.spring.project.use_cases.common.dto.UpdateEventDTO;
import yte.intern.spring.project.use_cases.common.entity.Event;
import yte.intern.spring.project.use_cases.manage_events.mapper.AddEventMapper;
import yte.intern.spring.project.use_cases.manage_events.mapper.UpdateEventMapper;
import yte.intern.spring.project.use_cases.manage_events.service.ManageEventsService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manageEvents")
@Validated
public class ManageEventsController {
    
    private final ManageEventsService manageEventsService;
    private final AddEventMapper addEventMapper;
    private final UpdateEventMapper updateEventMapper;

    @GetMapping("/listAllEvents")
    public List<AddEventDTO> listAllEvents(){
        List<Event> allEvents = manageEventsService.listAllEvents();
        List<AddEventDTO> allEventsDTO = addEventMapper.mapToDto(allEvents);

        return allEventsDTO;
    }

    @PostMapping("/addNewEvent")
    public AddEventDTO addNewEvent(@Valid @RequestBody AddEventDTO eventDTO){
        eventDTO.setNumberOfParticipants((long) 0);
        Event event = addEventMapper.mapToEntity(eventDTO);
        Event dbEvent = manageEventsService.addEvent(event);

        return addEventMapper.mapToDto(dbEvent);
    }

    @PutMapping("/updateEvent/{eventNo}")
    public UpdateEventDTO updateEvent(@PathVariable @Size(max = 5, min = 5) String eventNo,@Valid @RequestBody UpdateEventDTO eventDTO){
        Event newEvent = updateEventMapper.mapToEntity(eventDTO);
        Event dbEvent = manageEventsService.updateEvent(eventNo, newEvent);

        return updateEventMapper.mapToDto(dbEvent);
    }

    @DeleteMapping("/deleteEvent/{eventNo}")
    public EventDTO deleteEvent(@PathVariable @Size(max = 5, min = 5) String eventNo){
        Event deletedEvent = manageEventsService.deleteEvent(eventNo);

        return addEventMapper.mapToDto(deletedEvent);
    }

}