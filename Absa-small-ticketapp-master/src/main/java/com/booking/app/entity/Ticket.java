package com.booking.app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ticket")
public class Ticket {

	private long ticketId;
	private String passengerName;
	private Date bookingDate;
	private String sourceStation;
	private String destStation;
	private String emailId;
	
	public Ticket() {
		
	}
	
	public Ticket(String passengerName, String sourceStation,String destStation , String emailId,Date bookingDate) {
		this.passengerName = passengerName;
		this.sourceStation = sourceStation;
		this.destStation = destStation;
		this.emailId = emailId;
		this.bookingDate = bookingDate;
	}
	
	@Id()
	@Column(name="ticket_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return ticketId;
	}
	public void setId(long id) {
		this.ticketId = id;
	}
	
	@Column(name = "passenger_name")
	public String getPassengerName() {
		return passengerName;
	}
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	
	@Column(name = "source_station")
	public String getSourceStation() {
		return sourceStation;
	}
	public void setSourceStation(String sourceStation) {
		this.sourceStation = sourceStation;
	}
	
	@Column(name = "email_address")
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	@Column(name = "booking_date")
	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	@Column(name = "destination_station")
	public String getDestStation() {
		return destStation;
	}

	public void setDestStation(String destStation) {
		this.destStation = destStation;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + ticketId + ", passengerName=" + passengerName + ", sourceStation=" + sourceStation + ", emailId=" + emailId+
				
				", bookingDate=" + bookingDate+ ", destStation=" + destStation + "]";
	}

	
	
}
