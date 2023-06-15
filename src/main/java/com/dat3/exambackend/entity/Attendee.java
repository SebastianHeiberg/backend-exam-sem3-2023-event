package com.dat3.exambackend.entity;


import com.dat3.exambackend.dto.AttendeeRequest;
import com.dat3.security.entity.Role;
import com.dat3.security.entity.UserWithRoles;
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
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE")
public class Attendee extends UserWithRoles {


  int phoneNumber;
  @CreationTimestamp
  private LocalDateTime created;
  @UpdateTimestamp
  private LocalDateTime lastEdited;
  @OneToMany(mappedBy = "attendee", cascade = CascadeType.ALL)
  private List<EventAttendee> reservations = new ArrayList<>();


  public Attendee(String user, String password, String email, int phoneNumber) {
    super(user, password, email);
    this.phoneNumber = phoneNumber;
  }


  public Attendee (AttendeeRequest attendeeRequest){
    setUsername(attendeeRequest.getUsername());
    setEmail(attendeeRequest.getEmail());
    this.phoneNumber = attendeeRequest.getPhoneNumber();
    this.reservations = new ArrayList<>();
    this.setPassword(attendeeRequest.getPassword());
  }

  public void addReservation(EventAttendee eventAttendee){
    reservations.add(eventAttendee);
  }

  public void removeReservation(EventAttendee eventAttendee){
    reservations.remove(eventAttendee);
  }

}
