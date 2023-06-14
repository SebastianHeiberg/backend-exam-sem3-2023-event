package com.dat3.exambackend.dto;


import com.dat3.exambackend.entity.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventResponse {

  Long id;
  String name;
  LocalDateTime date;
  String description;
  int capacity;

  public EventResponse (Event event){
    this.id = event.getId();
    this.name = event.getName();
    this.date = event.getDate();
    this.description = event.getDescription();
    this.capacity = event.getCapacity();
  }

}
