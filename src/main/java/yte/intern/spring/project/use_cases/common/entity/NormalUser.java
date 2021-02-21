package yte.intern.spring.project.use_cases.common.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("NORMAL")
public class NormalUser extends BaseUser{
    
    @Column(name = "USER_NO", unique = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userno")
    private Integer userNo;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "USERS_REGISTERED_EVENTS",
			joinColumns = @JoinColumn(name = "USER_ID"),
			inverseJoinColumns = @JoinColumn(name = "EVENT_ID"))
    private Set<Event> registeredEvents;
    
    public boolean alreadyRegisteredToEvent(Event event){
        return registeredEvents.stream().anyMatch(e -> e.getEventNo().equals(event.getEventNo()));
    }
}