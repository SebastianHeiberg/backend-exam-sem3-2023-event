package com.dat3.exambackend.api;


import com.dat3.exambackend.service.EventAttendeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/attendeeEvent")
public class EventAttendeeController {

EventAttendeeService eventAttendeeService;

  public EventAttendeeController(EventAttendeeService eventAttendeeService) {
    this.eventAttendeeService = eventAttendeeService;
  }

  //kun bruger burde have adgang
  @PostMapping("/{username}/{eventId}")
  public ResponseEntity<Boolean> register(@PathVariable Long eventId, @PathVariable String username){
    return eventAttendeeService.createReservation(username, eventId);
  }


}
