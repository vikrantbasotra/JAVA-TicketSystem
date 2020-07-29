package com.vikrant.ticketsystem.dto;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.vikrant.ticketsystem.dao.entity.Seat;

import lombok.Data;

@Data
public class SeatHold {
	private ReservationStatus holdStatus; /* 0 : Success 1 Unable to do so 3 :Other error*/
	private List<Seat> seatList;
	private Timestamp holdStartTS;
	private String emailID;
	private Integer seatHoldID;
	
	
	private SeatHold(List<Seat> seatList, String emailID) {
		super();
		this.seatList = seatList;
		this.holdStartTS = new Timestamp((new Date()).getTime());
		this.emailID = emailID;
		this.seatHoldID = hashCode();
		this.holdStatus = ReservationStatus.UNKNOWN_ERROR;
	}

	public static SeatHold createAHold (List<Seat> seatList, String emailID) {
		SeatHold seatHold = new SeatHold(seatList, emailID);
		return (seatHold);
	}
}
