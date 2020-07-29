package com.vikrant.ticketsystem.dao.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.vikrant.ticketsystem.dao.entity.OnHoldBooking;

public interface OnHoldBookingRepository extends JpaRepository<OnHoldBooking, Integer> {
	public List<Integer> getListOfExpiredHoldIds(@Param("timeStamp") Timestamp timeStamp);

}
