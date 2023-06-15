package com.dat3.exambackend.api;


import com.dat3.exambackend.dto.AttendeeRequest;
import com.dat3.exambackend.dto.AttendeeResponse;
import com.dat3.exambackend.dto.EventRequest;
import com.dat3.exambackend.dto.EventResponse;
import com.dat3.exambackend.service.AttendeeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin
@RequestMapping("api/attendee")
public class AttendeeController {


  AttendeeService attendeeService;

  public AttendeeController(AttendeeService attendeeService) {
    this.attendeeService = attendeeService;
  }

  //alle kan oprette nyt medlem
  @PostMapping()
  public AttendeeResponse makeAttendee(@RequestBody AttendeeRequest attendeeRequest) {
    return attendeeService.saveAttendee(attendeeRequest);
  }

  //man skal kun kunne rette eget medlem
  @PutMapping()
  public AttendeeResponse editMember(@RequestBody AttendeeRequest body, Principal p){
    return attendeeService.editAttendee(body,p.getName());
  }


}
