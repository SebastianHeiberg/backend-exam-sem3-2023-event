package com.dat3.exambackend.dto;


import com.dat3.exambackend.entity.Attendee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AttendeeResponse {

  String username;
  String email;
  int phoneNumber;

  public AttendeeResponse(Attendee attendee){
    this.username = attendee.getUsername();
    this.email = attendee.getEmail();
    this.phoneNumber = attendee.getPhoneNumber();
  }

}
