package yte.intern.spring.project.use_cases.register_events.controller;

import java.lang.instrument.IllegalClassFormatException;
import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import yte.intern.spring.project.use_cases.register_events.mapper.RegisterEventMapper;
import yte.intern.spring.project.use_cases.register_events.service.RegisterEventsService;
import yte.intern.spring.project.use_cases.common.dto.RegisterEventDTO;
import yte.intern.spring.project.use_cases.common.entity.Event;

@RestController
@RequiredArgsConstructor
@RequestMapping("/normalUser")
@Validated
public class RegisterEventsController {
    
    private final RegisterEventsService registerEventsService;
    private final RegisterEventMapper registerEventMapper;

    @GetMapping("/listRelatedEvents")
    public List<RegisterEventDTO> listRelatedEvents(){
        List<Event> allEvents = registerEventsService.listRelatedEvents();
        List<RegisterEventDTO> allEventsDTO = registerEventMapper.mapToDto(allEvents);

        return allEventsDTO;
    }

    @PostMapping("/registerEvents/{tcKimlikNo}/{eventNo}")
    public RegisterEventDTO registerEvent(@PathVariable String tcKimlikNo, @PathVariable String eventNo){
        try{
            Event registeredEvent = registerEventsService.registerEvent(tcKimlikNo, eventNo);
            RegisterEventDTO registeredEventDTO = registerEventMapper.mapToDto(registeredEvent);
            return registeredEventDTO;
        }
        catch(IllegalStateException e){
            RegisterEventDTO alreadyRegistered = new RegisterEventDTO();
            alreadyRegistered.setCapacity((long) -1);
            return alreadyRegistered;
        }
        catch(IllegalClassFormatException e){
            RegisterEventDTO capacityFull = new RegisterEventDTO();
            capacityFull.setCapacity((long) -2);
            return capacityFull;
        }

    }

}