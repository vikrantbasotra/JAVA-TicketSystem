package com.vikrant.ticketsystem.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.vikrant.ticketsystem.dto.SeatStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Seat")
@NamedQuery(name = "Seat.numSeatsAvailable", query = "SELECT COUNT (*) AS available_seats FROM Seat WHERE seat_status =:status ")
@NamedQuery(name = "Seat.listOfSeatsAvailable", query = "FROM Seat WHERE seat_status =:status")
@NamedQuery(name = "Seat.listOfSeatsByHoldId", query = "FROM Seat WHERE reservation_id =:holdId")
@NamedQuery(name = "Seat.listOfSeatsByHoldIds", query = "FROM Seat WHERE reservation_id IN :holdIds")

public class Seat {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "seat_number", nullable = false)
	private int seatNumber;

	@Column(name = "row_number", nullable = false)
	private int rowNumber;

	@Column(name = "column_number", nullable = false)
	private int colNumber;

	@Column(name = "seat_status", nullable = false)
	private SeatStatus Status;

	@Column(name = "reservation_id")
	private String reservationId;
}
