package com.dat3.exambackend.config;


import com.dat3.exambackend.entity.Event;
import com.dat3.exambackend.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DeveloperData implements CommandLineRunner {

 @Autowired
  EventRepository eventRepository;

  @Override
  public void run(String... args) throws Exception {

    Event event = new Event("første-event", LocalDateTime.of(2023,06,10,07,06),"Min fest",1);
    eventRepository.save(event);
    Event event2 = new Event("andet-event", LocalDateTime.of(2023,07,10,07,06),"din fest",2);
    eventRepository.save(event2);
  }
}
