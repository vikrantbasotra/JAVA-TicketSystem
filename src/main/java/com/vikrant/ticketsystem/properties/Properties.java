package com.vikrant.ticketsystem.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@PropertySource("classpath:config.properties")
@Getter
@Setter
public class Properties {

	@Value("${ticketHoldTime}")
	private int ticketHoldTime;

	@Value("${rows}")
	private int auditoriumRows;

	@Value("${cols}")
	private int auditoriumCols;

}
