package yte.intern.spring.project.use_cases.common.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import yte.intern.spring.project.use_cases.common.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long>{

    @Query("select e FROM Event e WHERE e.startDate >= (:date)")
    List<Event> findEventsWithLaterDates(@Param("date") LocalDateTime nowTime);
    
    Optional<Event> findByEventNo(String eventNo);

    @Transactional
    void deleteByEventNo(String eventNo);

}