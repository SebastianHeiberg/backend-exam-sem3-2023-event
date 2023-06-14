package com.dat3.exambackend.service;

import com.dat3.exambackend.dto.AttendeeRequest;
import com.dat3.exambackend.entity.Attendee;
import com.dat3.exambackend.repository.AttendeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AttendeeServiceTest {

  @Autowired
  AttendeeRepository attendeeRepository;

  AttendeeService attendeeService;

  @BeforeEach
  void setUp() {
    attendeeService = new AttendeeService(attendeeRepository);
    Attendee attendee = new Attendee("test","Test@gmail.com",23);
    attendeeRepository.save(attendee);
  }

  @Test
  void saveAttendee() {
    AttendeeRequest attendeeRequest = new AttendeeRequest("test2","Test2@gmail.com",34);
    attendeeService.saveAttendee(attendeeRequest);
    assertEquals(2,attendeeRepository.findAll().size());

  }

  @Test
  void editAttendee() {
    AttendeeRequest attendeeRequest = new AttendeeRequest("test","anew@gmail.com",23);
    attendeeService.editAttendee(attendeeRequest,"test");

    assertEquals("anew@gmail.com",attendeeRepository.findById("test").get().getEmail());

  }
}