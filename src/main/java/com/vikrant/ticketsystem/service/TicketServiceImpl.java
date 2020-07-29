package com.vikrant.ticketsystem.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vikrant.ticketsystem.dao.entity.ConfirmBooking;
import com.vikrant.ticketsystem.dao.entity.OnHoldBooking;
import com.vikrant.ticketsystem.dao.entity.Seat;
import com.vikrant.ticketsystem.dao.repository.ConfirmBookingRepository;
import com.vikrant.ticketsystem.dao.repository.OnHoldBookingRepository;
import com.vikrant.ticketsystem.dao.repository.SeatRepository;
import com.vikrant.ticketsystem.dto.ReservationStatus;
import com.vikrant.ticketsystem.dto.SeatHold;
import com.vikrant.ticketsystem.dto.SeatReserved;
import com.vikrant.ticketsystem.dto.SeatStatus;

@Service("TicketServiceImpl")
public class TicketServiceImpl implements TicketService {
	private static final Logger LOGGER = LoggerFactory.getLogger(TicketServiceImpl.class);

	@Autowired
	private ConfirmBookingRepository confirmBookingRepository;

	@Autowired
	private SeatRepository seatRepository;

	@Autowired
	private OnHoldBookingRepository onHoldBookingRepository;

	@Override
	public Integer numSeatsAvailable() {
		return seatRepository.numSeatsAvailable(SeatStatus.AVAILABLE);
	}

	@Override
	@Transactional
	public SeatHold findAndHoldSeats(Integer numSeats, String customerEmail) {
		LOGGER.info("ENTER");
		Timestamp timeStamp = new Timestamp((new Date()).getTime());
		SeatHold seatHoldReturn;
		OnHoldBooking onHoldBooking_entity = new OnHoldBooking();
		Seat seat;

		List<Seat> seatListResponse = new ArrayList<Seat>();
		List<Seat> seatList_Available = seatRepository.listOfSeatsAvailable(SeatStatus.AVAILABLE);

		if (seatList_Available.size() >= numSeats) {
			seatHoldReturn = SeatHold.createAHold(seatListResponse, customerEmail);

			for (int i = 0; i < numSeats; i++) {
				seat = seatList_Available.get(i);
				seat.setStatus(SeatStatus.ONHOLD);
				seat.setReservationId(Integer.toString(seatHoldReturn.getSeatHoldID()));
				seatListResponse.add(seat);
			}

			seatRepository.saveAll(seatList_Available);

			onHoldBooking_entity.setHoldId(seatHoldReturn.getSeatHoldID());
			onHoldBooking_entity.setEmailId(customerEmail);
			onHoldBooking_entity.setTimeStamp(timeStamp);
			onHoldBookingRepository.save(onHoldBooking_entity);

			seatHoldReturn.setHoldStartTS(timeStamp);
			seatHoldReturn.setHoldStatus(ReservationStatus.SUCCESS);

		} else {
			seatHoldReturn = SeatHold.createAHold(seatListResponse, customerEmail);
			seatHoldReturn.setHoldStartTS(null);
			seatHoldReturn.setHoldStatus(ReservationStatus.UNKNOWN_ERROR);
		}

		return (seatHoldReturn);

	}

	@Override
	@Transactional
	public SeatReserved reserveSeats(Integer seatHoldId) {

		LOGGER.info("seatHoldId recieved Z: "+ seatHoldId);
		SeatReserved seatReserved = new SeatReserved();
		OnHoldBooking onHoldBooking = onHoldBookingRepository.findById(seatHoldId).orElse(null);

		ConfirmBooking confirmBooking = new ConfirmBooking();
		confirmBooking.setBookingId(seatHoldId);
		confirmBooking.setEmailId(onHoldBooking.getEmailId());

		Timestamp timeStamp = new Timestamp((new Date()).getTime());
		confirmBooking.setTimeStamp(timeStamp);

		seatReserved.setBookingID(onHoldBooking.getHoldId());
		seatReserved.setBookingStatus(ReservationStatus.SUCCESS);
		seatReserved.setBookingTS(timeStamp);
		seatReserved.setEmailID(onHoldBooking.getEmailId());

		onHoldBookingRepository.deleteById(seatHoldId);
		List<Seat> seatList;
		seatList = seatRepository.listOfSeatsByHoldId(Integer.toString(seatHoldId));
		for (Seat seat : seatList) {
			seat.setStatus(SeatStatus.BOOKED);
			seatRepository.save(seat);
		}

		confirmBookingRepository.save(confirmBooking);

		return seatReserved;
	}

}
