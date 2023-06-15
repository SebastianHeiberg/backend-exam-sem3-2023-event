package com.dat3.exambackend.service;

import com.dat3.exambackend.dto.AttendeeRequest;
import com.dat3.exambackend.dto.AttendeeResponse;
import com.dat3.exambackend.entity.Attendee;
import com.dat3.exambackend.repository.AttendeeRepository;
import com.dat3.security.entity.Role;
import com.dat3.security.repository.UserWithRolesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class AttendeeService {

  AttendeeRepository attendeeRepository;

  UserWithRolesRepository userWithRolesRepository;

  public AttendeeService(AttendeeRepository attendeeRepository, UserWithRolesRepository userWithRolesRepository) {
    this.attendeeRepository = attendeeRepository;
    this.userWithRolesRepository = userWithRolesRepository;
  }

  public AttendeeResponse saveAttendee(AttendeeRequest attendeeRequest) {
    Attendee attendee = new Attendee(attendeeRequest.getUsername(),attendeeRequest.getPassword(),attendeeRequest.getEmail(),attendeeRequest.getPhoneNumber());
    attendee.addRole(Role.USER);
    attendeeRepository.save(attendee);

    return new AttendeeResponse(attendee);
  }

  public AttendeeResponse editAttendee(AttendeeRequest body, String username) {

    Attendee attendeeFromDB = attendeeRepository.findById(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with this username dont exist"));
    Attendee attendeeUpdate = new Attendee(body);

    attendeeFromDB.setEmail(attendeeUpdate.getEmail());
    attendeeFromDB.setPhoneNumber(attendeeUpdate.getPhoneNumber());
    attendeeRepository.save(attendeeFromDB);
    return new AttendeeResponse(attendeeFromDB);
  }
}
