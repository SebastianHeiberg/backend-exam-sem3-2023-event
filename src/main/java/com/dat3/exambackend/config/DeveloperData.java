package com.dat3.exambackend.config;


import com.dat3.exambackend.entity.Attendee;
import com.dat3.exambackend.entity.Event;
import com.dat3.exambackend.repository.AttendeeRepository;
import com.dat3.exambackend.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DeveloperData implements CommandLineRunner {

 @Autowired
  EventRepository eventRepository;

 @Autowired
  AttendeeRepository attendeeRepository;

  @Override
  public void run(String... args) throws Exception {

    makeEvents();
    makeMembers();

  }


  public void makeMembers(){

    Attendee attendee1 = new Attendee("Sebastian01","sebastian@gmail.com",11020033);
    Attendee attendee2 = new Attendee("Marcus02","marcus@gmail.com",222030044);
    Attendee attendee3 = new Attendee("Tommy03","tommy@gmail.com",33300334);
    Attendee attendee4 = new Attendee("Bjørn04","bjørn@gmail.com",132414);

    attendeeRepository.save(attendee1);
    attendeeRepository.save(attendee2);
    attendeeRepository.save(attendee3);
    attendeeRepository.save(attendee4);

  }


  public void makeEvents(){
    Event event1 = new Event("Music Festival", LocalDateTime.of(2023, 6, 20, 18, 0),
        "Live performances by top artists. Join us for a night of music and fun!", 2);
    Event event2 = new Event("Art Exhibition", LocalDateTime.of(2023, 7, 5, 10, 0),
        "Explore captivating artworks from talented local artists. Free entry!", 3);
    Event event3 = new Event("Tech Conference", LocalDateTime.of(2023, 8, 15, 9, 30),
        "Learn about the latest trends in technology. Expert speakers and networking opportunities.", 5);
    Event event4 = new Event("Fitness Workshop", LocalDateTime.of(2023, 9, 1, 16, 0),
        "Join us for an energetic workshop. Stay fit and have fun!", 50);
    Event event5 = new Event("Food Tasting", LocalDateTime.of(2023, 9, 10, 14, 0),
        "Indulge in a variety of delicious cuisines. Limited spots available.", 0);
    Event event6 = new Event("Charity Run", LocalDateTime.of(2023, 10, 8, 8, 0),
        "Support a noble cause by participating in a charity run. All proceeds go to charity.", 1000);
    Event event7 = new Event("Movie Night", LocalDateTime.of(2023, 10, 22, 19, 30),
        "Enjoy a movie under the stars. Bring your blankets and popcorn!", 300);
    Event event8 = new Event("Business Seminar", LocalDateTime.of(2023, 11, 5, 13, 0),
        "Gain valuable insights from industry experts. Expand your business knowledge.", 150);
    Event event9 = new Event("Photography Workshop", LocalDateTime.of(2023, 12, 2, 11, 0),
        "Learn photography techniques from professionals. Suitable for all skill levels.", 80);
    Event event10 = new Event("Fashion Show", LocalDateTime.of(2023, 12, 15, 20, 0),
        "Witness the latest fashion trends. Glamour and style on the runway!", 500);
    eventRepository.save(event1);
    eventRepository.save(event2);
    eventRepository.save(event3);
    eventRepository.save(event4);
    eventRepository.save(event5);
    eventRepository.save(event6);
    eventRepository.save(event7);
    eventRepository.save(event8);
    eventRepository.save(event9);
    eventRepository.save(event10);


  }

}
