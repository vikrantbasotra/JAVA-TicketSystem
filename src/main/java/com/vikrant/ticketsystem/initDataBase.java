package com.vikrant.ticketsystem;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vikrant.ticketsystem.dao.entity.Seat;
import com.vikrant.ticketsystem.dao.repository.SeatRepository;
import com.vikrant.ticketsystem.dto.SeatStatus;
import com.vikrant.ticketsystem.properties.Properties;

@Configuration
public class initDataBase {
	private static final Logger LOG = LoggerFactory.getLogger(initDataBase.class);

	@Autowired
	Properties properties;

	@Autowired
	HoldSeatsCleanUp holdSeatsCleanUp;

	@Bean
	@Transactional
	CommandLineRunner setupDataBase(SeatRepository seatRepository) {

		int rows = properties.getAuditoriumRows();
		int cols = properties.getAuditoriumCols();
		if (0 == seatRepository.count()) { // INITILIZE AUDITORIUM in DB if DB is empty
			new Seat();

			for (int row = 0; row < rows; row++) {
				for (int col = 0; col < cols; col++) {
					Seat seat = new Seat();
					seat.setRowNumber(row);
					seat.setColNumber(col);
					seat.setStatus(SeatStatus.AVAILABLE);
					seat.setReservationId(null);

					seatRepository.save(seat);

				}
			}
		}

		Thread cleanUpThread = new Thread(holdSeatsCleanUp, "Hold Seats CleanUp");
		cleanUpThread.setDaemon(true);
		cleanUpThread.start();

		return args -> {
			LOG.info("Data Base: Auditorium Initilized (" + rows + "X" + cols + ")" + "........");
		};
	}

}
