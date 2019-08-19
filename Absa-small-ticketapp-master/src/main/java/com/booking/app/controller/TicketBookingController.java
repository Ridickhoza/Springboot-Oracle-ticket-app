package com.booking.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booking.app.entity.Ticket;
import com.booking.app.entity.User;
import com.booking.app.exceptions.ResourceNotFoundException;
import com.booking.app.repository.TicketRepository;
import com.booking.app.service.UserService;





@RestController
@RequestMapping("/api/v1")
public class TicketBookingController {
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private UserService userService;

	//User loging and registration
				@RequestMapping(value="/user",method = RequestMethod.GET)
				public String hello() {
					return "Welcome to springboot";
				}
				
				//User registration
				@RequestMapping(path="/user", method = RequestMethod.POST, consumes ="application/json")
				public User createUser(@RequestBody User user)
				{
					return this.userService.createUser(user);
				}
				
				//user login
				@RequestMapping(path="/user/{email}/{password}", method = RequestMethod.GET, produces ="application/json")
				public String getUser(@PathVariable(name="email") String email,@PathVariable(name="password") String password)
				{
					String getPassword = userService.returnUser(email, password).getPassword();
					
					
					return getPassword ;
					
				}
				
				
				
	//Crude operations
	
	@RequestMapping(path="/create", method = RequestMethod.POST, consumes ="application/json")
	public Ticket createTickets(@RequestBody Ticket ticket)
	{
		return this.ticketRepository.save(ticket);
	}
	
	
	
	
	@GetMapping("/tickets")
	public List<Ticket> getAllEmployees() {
		return ticketRepository.findAll();
	}

	@GetMapping("/tickets/{id}")
	public ResponseEntity<Ticket> getEmployeeById(@PathVariable(value = "id") Long ticketId)
			throws ResourceNotFoundException {
		Ticket ticket = ticketRepository.findById(ticketId)
				.orElseThrow(() -> new ResourceNotFoundException("Ticket not found for this id :: " + ticketId));
		return ResponseEntity.ok().body(ticket);
	}

	@PostMapping("/tickets")
	public Ticket createTicket(@Valid @RequestBody Ticket ticket) {
		return ticketRepository.save(ticket);
	}

	@PutMapping("/tickets/{id}")
	public ResponseEntity<Ticket> updateTicket(@PathVariable(value = "id") Long ticketId,
			@Valid @RequestBody Ticket ticketDetails) throws ResourceNotFoundException {
		Ticket ticket = ticketRepository.findById(ticketId)
				.orElseThrow(() -> new ResourceNotFoundException("Ticket not found for this id :: " + ticketId));

		ticket.setEmailId(ticketDetails.getEmailId());
		ticket.setSourceStation(ticketDetails.getSourceStation());
		ticket.setPassengerName(ticketDetails.getPassengerName());
		ticket.setBookingDate(ticketDetails.getBookingDate());
		ticket.setDestStation(ticketDetails.getDestStation());
		final Ticket updatedTicket = ticketRepository.save(ticket);
		return ResponseEntity.ok(updatedTicket);
	}

	@DeleteMapping("/tickets/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long ticketId)
			throws ResourceNotFoundException {
		Ticket ticket = ticketRepository.findById(ticketId)
				.orElseThrow(() -> new ResourceNotFoundException("Ticket not found for this id :: " + ticketId));

		ticketRepository.delete(ticket);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	

}
