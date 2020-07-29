package com.vikrant.ticketsystem.dao.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "On_Hold_Booking")
@NamedQuery(name = "OnHoldBooking.getListOfExpiredHoldIds", query = "SELECT holdId FROM OnHoldBooking WHERE timeStamp <= :timeStamp")

public class OnHoldBooking {
	@Id
	@Column(name = "hold_id", nullable = false)
	private Integer holdId;

	@Column(name = "email_id", nullable = false)
	private String emailId;

	@Column(name = "time_stamp", nullable = false)
	private Timestamp timeStamp;
}
