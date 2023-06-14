package com.dat3.exambackend.entity;


import com.dat3.exambackend.dto.EventRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Event {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  String name;
  LocalDateTime date;
  String description;
  int capacity;

  @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
  private List<EventAttendee> reservations = new ArrayList<>();
  @CreationTimestamp
  private LocalDateTime created;
  @UpdateTimestamp
  private LocalDateTime lastEdited;

  public Event(String name, LocalDateTime date, String description, int capacity) {
    this.name = name;
    this.date = date;
    this.description = description;
    this.capacity = capacity;
  }

  public Event (EventRequest eventRequest){
    this.name = eventRequest.getName();
    this.date = eventRequest.getDate();
    this.description = eventRequest.getDescription();
    this.capacity = eventRequest.getCapacity();
  }

}
