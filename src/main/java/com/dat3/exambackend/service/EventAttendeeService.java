package com.dat3.exambackend.service;


import com.dat3.exambackend.entity.Attendee;
import com.dat3.exambackend.entity.Event;
import com.dat3.exambackend.entity.EventAttendee;
import com.dat3.exambackend.repository.AttendeeRepository;
import com.dat3.exambackend.repository.EventAttendeeRepository;
import com.dat3.exambackend.repository.EventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EventAttendeeService {

  EventAttendeeRepository eventAttendeeRepository;
  AttendeeRepository attendeeRepository;
  EventRepository eventRepository;

  public EventAttendeeService(EventAttendeeRepository eventAttendeeRepository, AttendeeRepository attendeeRepository, EventRepository eventRepository) {
    this.eventAttendeeRepository = eventAttendeeRepository;
    this.attendeeRepository = attendeeRepository;
    this.eventRepository = eventRepository;
  }

  public ResponseEntity<Boolean> createReservation(String username, Long eventId) {

    Attendee attendee = attendeeRepository.findById(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with this username dont exist"));
    Event event = eventRepository.findById(eventId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event with this ID dont exist"));

    //save eventAttendee
    EventAttendee eventAttendee = new EventAttendee(attendee, event);
    eventAttendeeRepository.save(eventAttendee);

    return new ResponseEntity<>(true, HttpStatus.OK);
  }
}
