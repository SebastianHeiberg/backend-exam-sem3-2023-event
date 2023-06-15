package com.dat3.exambackend.service;


import com.dat3.exambackend.dto.EventResponse;
import com.dat3.exambackend.entity.Attendee;
import com.dat3.exambackend.entity.Event;
import com.dat3.exambackend.entity.EventAttendee;
import com.dat3.exambackend.repository.AttendeeRepository;
import com.dat3.exambackend.repository.EventAttendeeRepository;
import com.dat3.exambackend.repository.EventRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    if (eventAttendeeRepository.existsByAttendee_UsernameAndEvent_Id(username,eventId)){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only one ticket pr event");
    }

    int bookingings = eventAttendeeRepository.findAllByEvent_Id(event.getId()).size();
    int ticketleft = event.getCapacity()-bookingings;

    if(ticketleft <= 0){
      return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    //save eventAttendee
    EventAttendee eventAttendee = new EventAttendee(attendee, event);
    eventAttendeeRepository.save(eventAttendee);
    return new ResponseEntity<>(true, HttpStatus.OK);
  }

  public List<EventResponse> getAllMyEvents(String username) {

    Attendee attendee = attendeeRepository.findById(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with this username dont exist"));
    List<EventAttendee> eventAttendees = eventAttendeeRepository.findAllByAttendee_Username(attendee.getUsername());

    List<EventResponse> events = eventAttendees.stream().map(eventAttendee -> new EventResponse(eventAttendee.getEvent())).toList();
    return events;

  }

  public ResponseEntity<Boolean> removeAttendence(String username, Long eventId) {

    try {
     EventAttendee eventAttendee = eventAttendeeRepository.findEventAttendeeByAttendee_UsernameAndEvent_Id(username,eventId);
      eventAttendeeRepository.delete(eventAttendee);
      return new ResponseEntity<>(true, HttpStatus.OK);

    } catch (DataAccessException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error deleting event", e);
    }
  }

  public List<EventResponse> searchEvents(String name, String search) {
    Attendee attendee = attendeeRepository.findById(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with this username dont exist"));
    List<EventAttendee> eventAttendees = eventAttendeeRepository.findAllByAttendee_Username(attendee.getUsername()).stream().toList();
    List<EventAttendee> filteredAttendees = eventAttendees.stream()
        .filter(event -> event.getEvent().getName().toUpperCase().contains(search.toUpperCase()))
        .toList();

    List<EventResponse> events = filteredAttendees.stream().map(eventAttendee -> new EventResponse(eventAttendee.getEvent())).toList();
    return events;
  }
}
