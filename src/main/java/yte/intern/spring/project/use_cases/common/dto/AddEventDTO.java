package yte.intern.spring.project.use_cases.common.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddEventDTO extends EventDTO{
    @NotBlank
    @Size(max = 5, min = 5)
    private String eventNo;
}