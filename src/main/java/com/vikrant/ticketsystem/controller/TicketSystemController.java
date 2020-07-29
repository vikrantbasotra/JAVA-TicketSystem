package com.vikrant.ticketsystem.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vikrant.ticketsystem.dto.SeatCount;
import com.vikrant.ticketsystem.dto.SeatHold;
import com.vikrant.ticketsystem.dto.SeatReserved;
import com.vikrant.ticketsystem.service.TicketServiceImpl;

@RestController
@RequestMapping("/ticketService")
public class TicketSystemController {
	private static final Logger LOGGER = LoggerFactory.getLogger(TicketSystemController.class);

	@Autowired
	private TicketServiceImpl ticketSystemService;

	// http://localhost:9191/ticketService/seatsAvailable
	@GetMapping("/seatsAvailable")
	public SeatCount numSeatsAvailable() {
		SeatCount seatCount = new SeatCount();
		seatCount.setNumber(ticketSystemService.numSeatsAvailable());
		return seatCount;
	}

	
	// http://localhost:9191/ticketService/findAndHoldSeats?seatCount=1&emailId=vikrant.basotra@gmail.com
	@PostMapping("/findAndHoldSeats")
	public SeatHold findAndHoldSeats(
			@RequestParam(name = "seatCount", required = false, defaultValue = "1") Integer seatCount,
			@RequestParam(name = "emailId", required = true) String emailId) {

		LOGGER.info("Request recieved @ /findAndHoldSeats with Input Params emailId " + emailId + "& seatCount "
				+ seatCount);
		
		return ticketSystemService.findAndHoldSeats(seatCount, emailId);
		
	}
    
	//http://localhost:9191/ticketService/reserveSeats?onHoldId=-743842048
	@PatchMapping("/reserveSeats")
	public SeatReserved reserveSeats(@RequestParam(name = "onHoldId", required = true) Integer onHoldId) {
		
		LOGGER.info("Request recieved @ /reserveSeats with Input Param onHoldId : " + onHoldId);
		return (ticketSystemService.reserveSeats(onHoldId));
	}

}
