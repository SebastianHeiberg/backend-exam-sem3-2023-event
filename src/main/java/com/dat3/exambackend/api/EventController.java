package com.dat3.exambackend.api;


import com.dat3.exambackend.dto.EventRequest;
import com.dat3.exambackend.dto.EventResponse;
import com.dat3.exambackend.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("api/event")

public class EventController {

  EventService eventService;

  public EventController(EventService eventService) {
    this.eventService = eventService;
  }

  //oprette nyt admin adgang
  @PostMapping()
  public void makeEntity(@RequestBody EventRequest eventRequest) {
    eventService.saveEvent(eventRequest);
  }

  //alle kan se alle events
  @GetMapping()
  public List<EventResponse> getEvents() {
    return eventService.getAllEvents();
  }

  //kun admin
  @GetMapping("/{id}")
  public EventResponse getEvent(@PathVariable Long id) {
    return eventService.getEvent(id);
  }

  //kun admin
  @DeleteMapping("/{id}")
  public ResponseEntity<Boolean> deleteEvent(@PathVariable Long id) {
    return eventService.deleteEvent(id);
  }

  //kun admin
  @PutMapping("/{id}")
  public EventResponse editMember(@RequestBody EventRequest body, @PathVariable Long id){
    return eventService.editEvent(body,id);
  }

}
