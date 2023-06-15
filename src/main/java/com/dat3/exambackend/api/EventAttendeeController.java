package com.dat3.exambackend.api;


import com.dat3.exambackend.dto.EventResponse;
import com.dat3.exambackend.service.EventAttendeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/attendeeEvent")
public class EventAttendeeController {

EventAttendeeService eventAttendeeService;

  public EventAttendeeController(EventAttendeeService eventAttendeeService) {
    this.eventAttendeeService = eventAttendeeService;
  }

  //kun bruger burde have adgang
  @PostMapping("/{eventId}")
  public ResponseEntity<Boolean> register(@PathVariable Long eventId, Principal p){
    return eventAttendeeService.createReservation(p.getName(), eventId);
  }

  //kun brugeren burde have adgang
  @PreAuthorize("hasAuthority('USER')")
  @GetMapping()
  public List<EventResponse> getMyEvents(Principal p) {
    return eventAttendeeService.getAllMyEvents(p.getName());
  }

  //kun brugeren burde have adgang
  @DeleteMapping("/{eventId}")
  public ResponseEntity<Boolean> removeEventParticipation(Principal p, @PathVariable Long eventId) {
    return eventAttendeeService.removeAttendence(p.getName(), eventId);
  }


}
