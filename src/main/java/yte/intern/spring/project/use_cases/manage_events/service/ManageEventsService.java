package yte.intern.spring.project.use_cases.manage_events.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import yte.intern.spring.project.use_cases.common.repository.EventRepository;
import yte.intern.spring.project.use_cases.common.entity.Event;

@Service
@RequiredArgsConstructor
public class ManageEventsService {

    private final EventRepository eventRepo;

    /*Event Related Services*/
    public Event addEvent(Event event){
        return eventRepo.save(event);
    }

    public List<Event> listAllEvents(){
        return eventRepo.findAll();
    }

    @Transactional
	public Event updateEvent(String eventNo, Event newEvent) {
        Optional<Event> dbEvent = eventRepo.findByEventNo(eventNo);
        try{
		    if (dbEvent.isPresent()) {
                updateEventFromDB(newEvent, dbEvent.get());
                return eventRepo.save(dbEvent.get());
		    } else {
		    	throw new EntityNotFoundException();
            }
        }
        catch(IllegalStateException e){
            throw new IllegalStateException();
        }

    }
    
	private void updateEventFromDB(Event newEvent, Event dbEvent) {
        dbEvent.setName(newEvent.getName());
        dbEvent.setAddress(newEvent.getAddress());
        dbEvent.setStartDate(newEvent.getStartDate());
        dbEvent.setEndDate(newEvent.getEndDate());
        dbEvent.setCapacity(newEvent.getCapacity());
        if(dbEvent.getCapacity() < dbEvent.getNumberOfParticipants()){
            throw new IllegalStateException();
        }
        dbEvent.setNumberOfParticipants(dbEvent.getNumberOfParticipants());
	}

    public Event deleteEvent(String eventNo){
        Optional<Event> dbEvent = eventRepo.findByEventNo(eventNo);
        if(dbEvent.isPresent()){
            eventRepo.deleteByEventNo(eventNo);
            return dbEvent.get();
        }
        else{
            throw new EntityNotFoundException();
        }
    }
    
}