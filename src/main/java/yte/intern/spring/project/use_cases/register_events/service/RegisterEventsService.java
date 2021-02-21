package yte.intern.spring.project.use_cases.register_events.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toList;

import java.lang.instrument.IllegalClassFormatException;

import javax.crypto.IllegalBlockSizeException;
import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import yte.intern.spring.project.use_cases.common.repository.BaseUserRepository;
import yte.intern.spring.project.use_cases.common.repository.EventRepository;
import yte.intern.spring.project.use_cases.common.entity.BaseUser;
import yte.intern.spring.project.use_cases.common.entity.Event;
import yte.intern.spring.project.use_cases.common.entity.NormalUser;

@Service
@RequiredArgsConstructor
public class RegisterEventsService {
    
    private final BaseUserRepository userRepo;
    private final EventRepository eventRepo;

    public List<Event> listRelatedEvents(){
        return eventRepo.findEventsWithLaterDates(LocalDateTime.now());
    }

    public Event registerEvent(String tcKimlikNo, String eventNo) throws IllegalClassFormatException {

        Optional<NormalUser> normalUserOptional = userRepo.findByTcKimlikNo(tcKimlikNo);
        //If Base User found
        if(normalUserOptional.isPresent()){
            NormalUser normalUser =  normalUserOptional.get();
                
            Optional<Event> eventOptional = eventRepo.findByEventNo(eventNo);
            //If Event is found
            if(eventOptional.isPresent()){
                Event event = eventOptional.get();
                
                //If Event is not registered
                if(!normalUser.alreadyRegisteredToEvent(event)){
                    if(event.getNumberOfParticipants() + 1 <= event.getCapacity()){
                        event.setNumberOfParticipants(event.getNumberOfParticipants() + 1);
                        eventRepo.save(event);

                        Set<Event> registeredEvents = normalUser.getRegisteredEvents();
                        registeredEvents.add(event);
                        normalUser.setRegisteredEvents(registeredEvents);
                        NormalUser savedUser = userRepo.save(normalUser);
                        System.out.println("xxxxx");
                        return savedUser
                                .getRegisteredEvents()
                                .stream()
                                .filter(e -> e.getEventNo().equals(event.getEventNo()))
                                .collect(toList())
                                .get(0);
                    }
                    else{
                        throw new IllegalClassFormatException(); 
                    }
                }
                else{
                    System.out.println(event.getName());
                    throw new IllegalStateException(); 
                }
            }
            else{
                System.out.println("b");
                throw new EntityNotFoundException();    
            }
            
        }
        else{
            System.out.println("a");
            throw new EntityNotFoundException();
        }
    }

}