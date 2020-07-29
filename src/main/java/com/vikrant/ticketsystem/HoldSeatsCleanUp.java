package com.vikrant.ticketsystem;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vikrant.ticketsystem.dao.entity.Seat;
import com.vikrant.ticketsystem.dao.repository.OnHoldBookingRepository;
import com.vikrant.ticketsystem.dao.repository.SeatRepository;
import com.vikrant.ticketsystem.dto.SeatStatus;
import com.vikrant.ticketsystem.properties.Properties;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class HoldSeatsCleanUp implements Runnable {

	@Autowired
	private Properties properties;

	@Autowired
	private OnHoldBookingRepository onHoldBookingRepository;

	@Autowired
	private SeatRepository seatRepository;

	@Override
	public void run() {
		Integer sec = properties.getTicketHoldTime();

		while (true) {

			Timestamp timeStamp = new Timestamp(System.currentTimeMillis() - sec * 1000);

			List<Integer> listHoldIds = new ArrayList<Integer>();
			List<String> listHoldIds_Strings = new ArrayList<String>();
			List<Seat> listSeats = new ArrayList<Seat>();
			listHoldIds = onHoldBookingRepository.getListOfExpiredHoldIds(timeStamp);

			for (Integer holdId : listHoldIds) {
				onHoldBookingRepository.deleteById(holdId);
				listHoldIds_Strings.add(Integer.toString(holdId));

			}

			listSeats = seatRepository.listOfSeatsByHoldIds(listHoldIds_Strings);
			for (Seat seat : listSeats) {
				seat.setReservationId(null);
				seat.setStatus(SeatStatus.AVAILABLE);
				seatRepository.save(seat);
			}

			if (Thread.interrupted()) {
				return;
			}

		}

	}

}
