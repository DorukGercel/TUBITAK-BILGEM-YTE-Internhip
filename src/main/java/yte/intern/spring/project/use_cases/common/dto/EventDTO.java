package yte.intern.spring.project.use_cases.common.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import yte.intern.spring.project.use_cases.common.validators.EventDate;

@Getter
@Setter
@ToString
@EventDate
public abstract class EventDTO {

    @Size(max = 255, message = "Name can't be longer than 255 characters!")
    private String name;

    @Size(max = 255, message = "Address can't be longer than 255 characters!")
    private String address;

    @Future
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;
    
    @Future
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;
    
    @Min(value = 1, message = "Capacity must be at least 1!")
    private Long capacity;

    @Min(value = 0)
    private Long numberOfParticipants;
}