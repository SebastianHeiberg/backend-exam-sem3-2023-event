package com.dat3.exambackend.repository;

import com.dat3.exambackend.entity.Event;
import com.dat3.exambackend.entity.EventAttendee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventAttendeeRepository extends JpaRepository<EventAttendee,Long> {

  public List<EventAttendee> findAllByEvent_Id(Long id);
  public List<EventAttendee> findAllByAttendee_Username(String username);
  public void deleteByAttendee_UsernameAndEvent_Id(String username, Long eventId);
  public EventAttendee findEventAttendeeByAttendee_UsernameAndEvent_Id(String Username, Long eventId);
  public Boolean existsByAttendee_UsernameAndEvent_Id(String username, Long id);
}
