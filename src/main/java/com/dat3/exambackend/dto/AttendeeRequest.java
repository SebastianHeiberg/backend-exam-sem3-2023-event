package com.dat3.exambackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AttendeeRequest {

  String username;
  String email;
  int phoneNumber;
  String password;


}


