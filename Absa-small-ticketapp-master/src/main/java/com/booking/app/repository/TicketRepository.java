package com.booking.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.app.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{

}
