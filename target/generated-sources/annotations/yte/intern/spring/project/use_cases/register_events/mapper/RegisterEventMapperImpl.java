package yte.intern.spring.project.use_cases.register_events.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import yte.intern.spring.project.use_cases.common.dto.RegisterEventDTO;
import yte.intern.spring.project.use_cases.common.entity.Event;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-07T17:27:11+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 14 (Oracle Corporation)"
)
@Component
public class RegisterEventMapperImpl implements RegisterEventMapper {

    @Override
    public RegisterEventDTO mapToDto(Event event) {
        if ( event == null ) {
            return null;
        }

        RegisterEventDTO registerEventDTO = new RegisterEventDTO();

        registerEventDTO.setName( event.getName() );
        registerEventDTO.setAddress( event.getAddress() );
        registerEventDTO.setStartDate( event.getStartDate() );
        registerEventDTO.setEndDate( event.getEndDate() );
        registerEventDTO.setCapacity( event.getCapacity() );
        registerEventDTO.setEventNo( event.getEventNo() );
        registerEventDTO.setNumberOfParticipants(event.getNumberOfParticipants());

        return registerEventDTO;
    }

    @Override
    public List<RegisterEventDTO> mapToDto(List<Event> eventList) {
        if ( eventList == null ) {
            return null;
        }

        List<RegisterEventDTO> list = new ArrayList<RegisterEventDTO>( eventList.size() );
        for ( Event event : eventList ) {
            list.add( mapToDto( event ) );
        }

        return list;
    }

    @Override
    public Event mapToEntity(RegisterEventDTO eventDTO) {
        if ( eventDTO == null ) {
            return null;
        }

        Event event = new Event();

        event.setEventNo( eventDTO.getEventNo() );
        event.setName( eventDTO.getName() );
        event.setAddress( eventDTO.getAddress() );
        event.setStartDate( eventDTO.getStartDate() );
        event.setEndDate( eventDTO.getEndDate() );
        event.setCapacity( eventDTO.getCapacity() );
        event.setNumberOfParticipants(eventDTO.getNumberOfParticipants());

        return event;
    }

    @Override
    public List<Event> mapToEntity(List<RegisterEventDTO> eventDTOList) {
        if ( eventDTOList == null ) {
            return null;
        }

        List<Event> list = new ArrayList<Event>( eventDTOList.size() );
        for ( RegisterEventDTO registerEventDTO : eventDTOList ) {
            list.add( mapToEntity( registerEventDTO ) );
        }

        return list;
    }
}
