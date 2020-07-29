package com.vikrant.ticketsystem.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.vikrant.ticketsystem.dao.entity.Seat;
import com.vikrant.ticketsystem.dto.SeatStatus;

public interface SeatRepository extends JpaRepository<Seat, Integer> {

	public int numSeatsAvailable(@Param("status") SeatStatus status);

	public List<Seat> listOfSeatsAvailable(@Param("status") SeatStatus status);

	public List<Seat> listOfSeatsByHoldId(@Param("holdId") String holdId);

	public List<Seat> listOfSeatsByHoldIds(@Param("holdIds") List<String> holdIds);

}
