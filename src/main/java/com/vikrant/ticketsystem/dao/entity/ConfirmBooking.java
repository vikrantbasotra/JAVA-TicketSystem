package com.vikrant.ticketsystem.dao.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "Confirm_Booking")
@Entity
public class ConfirmBooking {
	@Id
	@Column(name = "booking_id", nullable = false)
	private Integer bookingId;

	@Column(name = "email_id", nullable = false)
	private String emailId;
	
	@Column(name = "time_stamp", nullable = false)
	private Timestamp timeStamp;

}