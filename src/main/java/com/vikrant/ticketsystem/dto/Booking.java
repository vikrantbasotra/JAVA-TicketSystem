package com.vikrant.ticketsystem.dto;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.vikrant.ticketsystem.dao.entity.Seat;

import lombok.Data;

@Data
public class Booking {
	private List<Seat> seatList;
	private Timestamp bookingTS;
	private String emailID;
	private String confirmationCode;

	private Booking(List<Seat> seatList, String emailID) {
		super();
		this.seatList = seatList;
		this.bookingTS = new Timestamp((new Date()).getTime());
		this.emailID = emailID;
		this.confirmationCode = null;
	}

	public static Booking createBooking(List<Seat> seatList, String emailID) {
		Booking booking = new Booking(seatList, emailID);
		return (booking);
	}

}
