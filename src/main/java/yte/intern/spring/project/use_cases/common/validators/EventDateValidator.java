package yte.intern.spring.project.use_cases.common.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import yte.intern.spring.project.use_cases.common.dto.EventDTO;


public class EventDateValidator implements ConstraintValidator<EventDate, EventDTO>{

    public void initialize(EventDate constraint) {
    }

    @Override
    public boolean isValid(EventDTO eventDTO, ConstraintValidatorContext context) {
        //If start date is after end date returns false
        if(eventDTO.getStartDate().isAfter(eventDTO.getEndDate())){
            return false;
        }
        //Else is valid and returns true
        return true;
    }

    
    
}