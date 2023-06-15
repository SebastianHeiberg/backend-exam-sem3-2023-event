package com.dat3.exambackend.api;


import com.dat3.exambackend.dto.EventRequest;
import com.dat3.exambackend.dto.EventResponse;
import com.dat3.exambackend.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
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
  @PreAuthorize("hasAuthority('ADMIN')")
  @PostMapping()
  public ResponseEntity<Boolean> makeEvent(@RequestBody EventRequest eventRequest) {
    return eventService.saveEvent(eventRequest);
  }

  //alle kan se alle events
  @GetMapping()
  public List<EventResponse> getEvents() {
    return eventService.getAllEvents();
  }

  //alle kan søge i alle events
  @GetMapping("/title/{title}")
  public List<EventResponse> getSpecificEvents(@PathVariable String title) {
    return eventService.getSpecificEvents(title);
  }

  //kun admin
  @PreAuthorize("hasAuthority('ADMIN')")
  @GetMapping("/{id}")
  public EventResponse getEvent(@PathVariable Long id) {
    return eventService.getEvent(id);
  }

  //kun admin
  @PreAuthorize("hasAuthority('ADMIN')")
  @DeleteMapping("/{id}")
  public ResponseEntity<Boolean> deleteEvent(@PathVariable Long id) {
    return eventService.deleteEvent(id);
  }

  //kun admin
  @PutMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public EventResponse editMember(@RequestBody EventRequest body, @PathVariable Long id){
    return eventService.editEvent(body,id);
  }

}
