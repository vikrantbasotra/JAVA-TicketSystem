package com.vikrant.ticketsystem.dto;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.vikrant.ticketsystem.dao.entity.Seat;

import lombok.Data;

@Data
public class SeatReserved {
	private ReservationStatus bookingStatus; /* 0 : Success 1 Unable to do so 3 :Other error */
	private List<Seat> seatList;
	private Timestamp bookingTS;
	private String emailID;
	private Integer bookingID;

	public SeatReserved() {
		super();
		this.seatList = null;
		this.bookingTS = new Timestamp((new Date()).getTime());
		this.emailID = null;
		this.bookingID = null;
		this.bookingStatus = ReservationStatus.UNKNOWN_ERROR;
	}
}
