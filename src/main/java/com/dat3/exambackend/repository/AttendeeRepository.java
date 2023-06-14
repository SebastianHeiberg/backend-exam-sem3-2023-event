package com.dat3.exambackend.repository;

import com.dat3.exambackend.entity.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendeeRepository extends JpaRepository<Attendee, String> {
}
