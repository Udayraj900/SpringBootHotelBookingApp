package com.mindtree.mystayapp.controller;

import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.mystayapp.dto.BookingCriteria;
import com.mindtree.mystayapp.exception.BookingException;
import com.mindtree.mystayapp.model.Booking;
import com.mindtree.mystayapp.model.Response;
import com.mindtree.mystayapp.service.BookingService;
import com.mindtree.mystayapp.util.Messages;

import static java.util.Objects.nonNull;

/**
 * @author RShaw
 * 
 *         Controller class to handle the room booking operation
 *
 */
@RestController
@RequestMapping(value = "/mystayapp")
public class BookingController {

	private static final Logger logger = LoggerFactory.getLogger(BookingController.class);
 
	private static final String INVALID_DATE_ERROR = Messages.getString("BookingController.INVALID_DATE_ERROR"); 
	@Autowired
	private BookingService bookingService;

	/**
	 * 
	 * @param bookingCritera
	 * @return
	 * @throws BookingException
	 *             Controller method to handle the room booking operations with
	 *             the proper inputs provided like room , no of rooms etc.
	 *             Before booking, verify current availability of that room ,and
	 *             then proceed for booking.
	 */
	@RequestMapping(value = "/bookroom", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Response> bookHotelsByAvailablity(@RequestBody BookingCriteria bookingCritera){

		Response response = new Response();

		if (nonNull(bookingCritera)) {

			if (null != bookingCritera.getFromDate() && null != bookingCritera.getToDate()) {
				if (bookingCritera.getFromDate().before(new Date(System.currentTimeMillis()))
						|| (bookingCritera.getFromDate().after(bookingCritera.getToDate()))) {
					logger.debug(INVALID_DATE_ERROR); 
					response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
					response.setMessage(INVALID_DATE_ERROR); 
					return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
				}

				Booking bookedRoom = bookingService.saveBooking(bookingCritera);
				response.setStatus(HttpStatus.CREATED.value());
				response.setMessage("Rooms booked successfully in the hotel " + bookedRoom.getHotel().getHotelName() 
						+ " with booking id : " + bookedRoom.getBookingId()); 
				logger.info("Room booked with booking Id " + bookedRoom.getBookingId() + " for the Customer :"  
						+ bookedRoom.getUser().getUserName());
				return new ResponseEntity<>(response, HttpStatus.CREATED);

			} else {
				response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
				response.setMessage(INVALID_DATE_ERROR); 
				return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
			}
		} else {
			logger.error("Inputs are not valid." + bookingCritera); 
			response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
			response.setMessage(Messages.getString("BookingController.INVALID_INPUTS")); 
			return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
		}

	}
}
