package com.dat3.exambackend.service;

import com.dat3.exambackend.dto.EventRequest;
import com.dat3.exambackend.entity.Event;
import com.dat3.exambackend.repository.EventAttendeeRepository;
import com.dat3.exambackend.repository.EventRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EventServiceTest {


  //bruges til at sætte auto-increment start til 1 igen
  @Autowired
  private EntityManager entityManager;

  @Autowired
  EventRepository eventRepository;

  @Autowired
  EventAttendeeRepository eventAttendeeRepository;

  EventService eventService;

  @BeforeEach
  void setUp() {
    eventService = new EventService(eventRepository, eventAttendeeRepository);
//    entityManager.createNativeQuery("ALTER TABLE EVENT ALTER COLUMN id RESTART WITH 1").executeUpdate();
    Event event = new Event("test", LocalDateTime.now(), "testi", 3);
    eventRepository.saveAndFlush(event);

  }

  @Test
  void saveEvent() {
    Event event = new Event("test3", LocalDateTime.now(), "testi", 3);
    eventRepository.save(event);
    int actual = eventRepository.findAll().size();
    int expected = 2;
    assertEquals(actual, expected);
  }

  @Test
  void getAllEvents() {
    int actual = eventRepository.findAll().size();
    int expected = 1;
    assertEquals(actual, expected);
  }



    @Test
    void deleteEvent() {
      Long id = 1L;
      eventService.deleteEvent(id);
      int actual = eventRepository.findAll().size();
      int expected = 0;
      assertEquals(actual, expected);


    }

    @Test
    void editEvent() {
      Long id = 1L;
      assertEquals("test", eventRepository.findById(id).get().getName());
      EventRequest eventRequest = new EventRequest("Sebastian", LocalDateTime.now(), "opdateret", 4);
      eventService.editEvent(eventRequest, id);
      assertEquals("Sebastian", eventRepository.findById(id).get().getName());
    }

  }
