package com.mindtree.mystayapp.service;

import com.mindtree.mystayapp.dto.BookingCriteria;
import com.mindtree.mystayapp.model.Booking;

public interface BookingService {

	public Booking saveBooking(BookingCriteria bookingCritera);

}
