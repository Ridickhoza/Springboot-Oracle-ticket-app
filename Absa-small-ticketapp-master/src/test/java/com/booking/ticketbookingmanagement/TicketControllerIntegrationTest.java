package com.booking.ticketbookingmanagement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

//import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.booking.app.Application;
import com.booking.app.entity.Ticket;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TicketControllerIntegrationTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testGetAllEmployees() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/tickets",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetEmployeeById() {
		Ticket ticket = restTemplate.getForObject(getRootUrl() + "/tickets/1", Ticket.class);
		System.out.println(ticket.getPassengerName());
		assertNotNull(ticket);
	}
	

	@Test
	public void testCreateEmployee() {
		//Date d= new Date();
		
		//String date = "20/05/2019";
		//SimpleDateFormat sdf = new  SimpleDateFormat("dd/MM/yyyy");
		
		Ticket ticket = new Ticket();
		ticket.setEmailId("admin@gmail.com");
		ticket.setPassengerName("admin");
		ticket.setSourceStation("Jozi");
		//ticket.setBookingDate(d);
		ticket.setDestStation("Cape Town");

		ResponseEntity<Ticket> postResponse = restTemplate.postForEntity(getRootUrl() + "/tickets", ticket, Ticket.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdateEmployee() {
		int id = 1;
		Ticket ticket = restTemplate.getForObject(getRootUrl() + "/tickets/" + id, Ticket.class);
		ticket.setPassengerName("admin1");
		ticket.setSourceStation("admin2");

		restTemplate.put(getRootUrl() + "/tickets/" + id, ticket);

		Ticket updatedTicket = restTemplate.getForObject(getRootUrl() + "/tickets/" + id, Ticket.class);
		assertNotNull(updatedTicket);
	}

	@Test
	public void testDeleteEmployee() {
		int id = 2;
		Ticket ticket = restTemplate.getForObject(getRootUrl() + "/tickets/" + id, Ticket.class);
		assertNotNull(ticket);

		restTemplate.delete(getRootUrl() + "/tickets/" + id);

		try {
			ticket = restTemplate.getForObject(getRootUrl() + "/tickets/" + id, Ticket.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
}
