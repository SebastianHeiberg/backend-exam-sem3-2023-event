package com.dat3.exambackend.entity;


import com.dat3.exambackend.dto.AttendeeRequest;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Attendee {

  @Id
  String username;
  String email;
  int phoneNumber;
  @CreationTimestamp
  private LocalDateTime created;
  @UpdateTimestamp
  private LocalDateTime lastEdited;
  @OneToMany(mappedBy = "attendee", cascade = CascadeType.ALL)
  private List<EventAttendee> reservations = new ArrayList<>();

  public Attendee(String username, String email, int phoneNumber) {
    this.username = username;
    this.email = email;
    this.phoneNumber = phoneNumber;
  }

  public Attendee (AttendeeRequest attendeeRequest){
    this.username = attendeeRequest.getUsername();
    this.email = attendeeRequest.getEmail();
    this.phoneNumber = attendeeRequest.getPhoneNumber();
    this.reservations = new ArrayList<>();
  }

  public void addReservation(EventAttendee eventAttendee){
    reservations.add(eventAttendee);
  }

  public void removeReservation(EventAttendee eventAttendee){
    reservations.remove(eventAttendee);
  }

}
