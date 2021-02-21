package yte.intern.spring.project.use_cases.common.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "idgen", sequenceName = "EVENT_SEQ")
public class Event extends BaseEntity{

    @Column(name = "EVENT_NO", unique = true)
    private String eventNo;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "START_DATE")
    private LocalDateTime startDate;
    
    @Column(name = "END_DATE")
    private LocalDateTime endDate;
    
    @Column(name = "CAPACITY")
    private Long capacity;
    
    @Column(name = "NUMBER_OF_PARTICIPANTS")
    private Long numberOfParticipants;

}