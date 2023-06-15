package com.dat3.exambackend.service;

import com.dat3.exambackend.entity.Attendee;
import com.dat3.exambackend.entity.Event;
import com.dat3.exambackend.entity.EventAttendee;
import com.dat3.exambackend.repository.AttendeeRepository;
import com.dat3.exambackend.repository.EventAttendeeRepository;
import com.dat3.exambackend.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class EventAttendeeServiceTest {

  @Autowired
  EventAttendeeRepository eventAttendeeRepository;
  @Autowired
  AttendeeRepository attendeeRepository;
  @Autowired
  EventRepository eventRepository;

  EventAttendeeService eventAttendeeService;

  @BeforeEach
  void setUp() {
    eventAttendeeService = new EventAttendeeService(eventAttendeeRepository, attendeeRepository, eventRepository);
    Attendee attendee = new Attendee("test","Test@gmail.com","test",23);
    attendeeRepository.save(attendee);
    Event event = new Event("test", LocalDateTime.now(), "testi", 3);
    eventRepository.saveAndFlush(event);
    EventAttendee eventAttendee = new EventAttendee(attendee,event);
    eventAttendeeRepository.save(eventAttendee);
  }

  @Test
  void createReservationfailOneTicketOnly() {
    Event event = new Event("test2", LocalDateTime.now(), "testi", 3);
    eventRepository.saveAndFlush(event);

    eventAttendeeService.createReservation("test",2L);
    assertEquals(2,eventAttendeeRepository.findAllByAttendee_Username("test").size());
  }

  @Test
  void getAllMyEvents() {

    assertEquals(1,eventAttendeeService.getAllMyEvents("test").size());
  }

  @Test
  void removeAttendence() {
    eventAttendeeService.removeAttendence("test",1L);
    assertEquals(0, eventAttendeeRepository.findAllByAttendee_Username("test").size());
  }
}