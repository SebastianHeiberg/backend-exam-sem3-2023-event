package com.dat3.exambackend.repository;

import com.dat3.exambackend.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}
