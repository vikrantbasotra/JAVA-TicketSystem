package com.vikrant.ticketsystem.service;

import com.vikrant.ticketsystem.dto.SeatHold;
import com.vikrant.ticketsystem.dto.SeatReserved;

public interface TicketService {
	
	Integer numSeatsAvailable();
	SeatHold findAndHoldSeats (Integer numSeats, String customerEmail);
	SeatReserved reserveSeats(Integer seatHoldId);
}
