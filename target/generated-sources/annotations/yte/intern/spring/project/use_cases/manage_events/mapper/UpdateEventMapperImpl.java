package yte.intern.spring.project.use_cases.manage_events.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import yte.intern.spring.project.use_cases.common.dto.UpdateEventDTO;
import yte.intern.spring.project.use_cases.common.entity.Event;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-07T17:27:11+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 14 (Oracle Corporation)"
)
@Component
public class UpdateEventMapperImpl implements UpdateEventMapper {

    @Override
    public UpdateEventDTO mapToDto(Event event) {
        if ( event == null ) {
            return null;
        }

        UpdateEventDTO updateEventDTO = new UpdateEventDTO();

        updateEventDTO.setName( event.getName() );
        updateEventDTO.setAddress( event.getAddress() );
        updateEventDTO.setStartDate( event.getStartDate() );
        updateEventDTO.setEndDate( event.getEndDate() );
        updateEventDTO.setCapacity( event.getCapacity() );

        return updateEventDTO;
    }

    @Override
    public List<UpdateEventDTO> mapToDto(List<Event> eventList) {
        if ( eventList == null ) {
            return null;
        }

        List<UpdateEventDTO> list = new ArrayList<UpdateEventDTO>( eventList.size() );
        for ( Event event : eventList ) {
            list.add( mapToDto( event ) );
        }

        return list;
    }

    @Override
    public Event mapToEntity(UpdateEventDTO eventDTO) {
        if ( eventDTO == null ) {
            return null;
        }

        Event event = new Event();

        event.setName( eventDTO.getName() );
        event.setAddress( eventDTO.getAddress() );
        event.setStartDate( eventDTO.getStartDate() );
        event.setEndDate( eventDTO.getEndDate() );
        event.setCapacity( eventDTO.getCapacity() );

        return event;
    }

    @Override
    public List<Event> mapToEntity(List<UpdateEventDTO> eventDTOList) {
        if ( eventDTOList == null ) {
            return null;
        }

        List<Event> list = new ArrayList<Event>( eventDTOList.size() );
        for ( UpdateEventDTO updateEventDTO : eventDTOList ) {
            list.add( mapToEntity( updateEventDTO ) );
        }

        return list;
    }
}
