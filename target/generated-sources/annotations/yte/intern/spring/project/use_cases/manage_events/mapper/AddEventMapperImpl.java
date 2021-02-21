package yte.intern.spring.project.use_cases.manage_events.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import yte.intern.spring.project.use_cases.common.dto.AddEventDTO;
import yte.intern.spring.project.use_cases.common.entity.Event;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-07T17:27:11+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 14 (Oracle Corporation)"
)
@Component
public class AddEventMapperImpl implements AddEventMapper {

    @Override
    public AddEventDTO mapToDto(Event event) {
        if ( event == null ) {
            return null;
        }

        AddEventDTO addEventDTO = new AddEventDTO();

        addEventDTO.setName( event.getName() );
        addEventDTO.setAddress( event.getAddress() );
        addEventDTO.setStartDate( event.getStartDate() );
        addEventDTO.setEndDate( event.getEndDate() );
        addEventDTO.setCapacity( event.getCapacity() );
        addEventDTO.setEventNo( event.getEventNo() );
        addEventDTO.setNumberOfParticipants(event.getNumberOfParticipants());

        return addEventDTO;
    }

    @Override
    public List<AddEventDTO> mapToDto(List<Event> eventList) {
        if ( eventList == null ) {
            return null;
        }

        List<AddEventDTO> list = new ArrayList<AddEventDTO>( eventList.size() );
        for ( Event event : eventList ) {
            list.add( mapToDto( event ) );
        }

        return list;
    }

    @Override
    public Event mapToEntity(AddEventDTO eventDTO) {
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
    public List<Event> mapToEntity(List<AddEventDTO> eventDTOList) {
        if ( eventDTOList == null ) {
            return null;
        }

        List<Event> list = new ArrayList<Event>( eventDTOList.size() );
        for ( AddEventDTO addEventDTO : eventDTOList ) {
            list.add( mapToEntity( addEventDTO ) );
        }

        return list;
    }
}
