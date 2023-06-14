package com.dat3.exambackend.service;

import com.dat3.exambackend.dto.EventRequest;
import com.dat3.exambackend.dto.EventResponse;
import com.dat3.exambackend.entity.Event;
import com.dat3.exambackend.repository.EventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EventService {

  EventRepository eventRepository;

  public EventService(EventRepository eventRepository) {
    this.eventRepository = eventRepository;
  }

  public void saveEvent(EventRequest eventRequest){
    Event event= new Event(eventRequest);
    eventRepository.save(event);
  }

  public List<EventResponse> getAllEvents(){
    List<EventResponse> events = eventRepository.findAll().stream().map(entity -> new EventResponse(entity)).toList();
    return events;

  }

  public EventResponse getEvent(Long id) {

    Event event = eventRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event with this id dont exist"));
    return new EventResponse(event);
  }

  public ResponseEntity<Boolean> deleteEvent(Long id) {
    if (eventRepository.existsById(id)) {
      eventRepository.deleteById(id);
      return new ResponseEntity<>(true, HttpStatus.OK);
    }
    return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
  }

  public EventResponse editEvent(EventRequest body, Long id) {

    Event eventFromDB = eventRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event with this id dont exist"));
    Event eventNewInfo = new Event(body);

    eventFromDB.setCapacity(eventNewInfo.getCapacity());
    eventFromDB.setName(eventNewInfo.getName());
    eventFromDB.setDate(eventNewInfo.getDate());
    eventFromDB.setDescription(eventNewInfo.getDescription());
    eventRepository.save(eventFromDB);
    return new EventResponse(eventFromDB);
  }
}
