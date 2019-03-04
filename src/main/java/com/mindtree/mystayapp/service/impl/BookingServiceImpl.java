package com.mindtree.mystayapp.service.impl;

import java.sql.Date;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.mindtree.mystayapp.dao.BookingRepository;
import com.mindtree.mystayapp.dao.RoomRepository;
import com.mindtree.mystayapp.dao.UserRepository;
import com.mindtree.mystayapp.dto.AvailableRoomsBySearchDTO;
import com.mindtree.mystayapp.dto.BookingCriteria;
import com.mindtree.mystayapp.dto.PaymentDTO;
import com.mindtree.mystayapp.exception.BookingException;
import com.mindtree.mystayapp.model.Booking;
import com.mindtree.mystayapp.model.Room;
import com.mindtree.mystayapp.model.User;
import com.mindtree.mystayapp.service.BookingService;
import com.mindtree.mystayapp.util.Messages;

/**
 * 
 * @author RShaw
 *
 */
@Service
@Transactional
public class BookingServiceImpl implements BookingService {

	private static final Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Booking saveBooking(BookingCriteria bookingCritera) throws BookingException {

		Booking bookingRoom = new Booking();
		Room room = roomRepository.findById(bookingCritera.getRoom()).orElse(null);
		User user = userRepository.findByUserName(bookingCritera.getUserName());
		if (null == room) {
			logger.debug(Messages.getString("BookingServiceImpl.ROOM_DETAIL_MISSING")+ bookingCritera.getRoom());
			throw new BookingException(HttpStatus.UNPROCESSABLE_ENTITY.value(), Messages.getString("BookingServiceImpl.ROOM_DETAIL_MISSING"));
		}
		AvailableRoomsBySearchDTO availableRoom = bookingRepository.getCurrentAvailableRooms(room.getHotel().getCity(),
				room.getRoomId(), bookingCritera.getFromDate(), bookingCritera.getToDate());
		if (null == availableRoom) {
			logger.debug(Messages.getString("BookingServiceImpl.ROOMS_ALREADY_BOOKED") + " for " + bookingCritera);
			throw new BookingException(HttpStatus.OK.value(), Messages.getString("BookingServiceImpl.ROOMS_ALREADY_BOOKED"));
		}
		if (null == user) {
			logger.debug(Messages.getString("BookingServiceImpl.USER_DETAIL_MISSING")+ bookingCritera.getUserName());
			throw new BookingException(HttpStatus.UNPROCESSABLE_ENTITY.value(),  Messages.getString("BookingServiceImpl.USER_DETAIL_MISSING"));
		}

		if (availableRoom.getCurrentAvailableRoomCount() >= bookingCritera.getNoOfBookedRooms()) {
			bookingRoom.setBookedRoom(room);
			bookingRoom.setHotel(room.getHotel());
			bookingRoom.setCheckInDate(bookingCritera.getFromDate());
			bookingRoom.setCheckOutDate(bookingCritera.getToDate());
			bookingRoom.setBookingDate(new Date(System.currentTimeMillis()));
			bookingRoom.setNoOfRooms(bookingCritera.getNoOfBookedRooms());
			bookingRoom.setUser(user);
			Long startTime = System.currentTimeMillis();
			
			PaymentDTO successPayment = new PaymentDTO();
			PaymentDTO paymentDTO = new PaymentDTO();
			Booking bookedSuccessRoom = new Booking();
			try {

				paymentDTO.setUserId(bookingRoom.getUser().getUserName());
				paymentDTO.setTotalAmount((bookingRoom.getNoOfRooms()) * (bookingRoom.getBookedRoom().getRoomPrice()));
				paymentDTO.setVendor("My Stay Company");  
				paymentDTO.setCardBalance(50000.0);
				startTime = System.currentTimeMillis();
				logger.info("Payment started.......: " + startTime); 
				successPayment = makePayment(Messages.getString("BookingServiceImpl.PAYMENT_URL"), paymentDTO);
				logger.info("Payment Completed Successfully in " +(System.currentTimeMillis() - startTime)+ " Milli Seconds. "+ successPayment); 
			} catch (HttpClientErrorException  e) {
				throw new BookingException(HttpStatus.NOT_IMPLEMENTED.value(),
						Messages.getString("BookingServiceImpl.PAYMENT_UNSUCCESSFULL"));  
			}
			if (successPayment.getCardBalance() < paymentDTO.getCardBalance()) {
				bookingRoom.setBookingStatus(Messages.getString("BookingServiceImpl.BOOKED_STATUS"));  
				bookedSuccessRoom = bookingRepository.save(bookingRoom);
				long endTime = System.currentTimeMillis();
				logger.info("Booking completed: " + System.currentTimeMillis());  
				logger.info("Booking completed in " + (endTime - startTime) + " Milli Seconds.");  
			}

			return bookedSuccessRoom;
		} else {
			throw new BookingException(HttpStatus.OK.value(),
					"Requested Number of rooms {" + bookingCritera.getNoOfBookedRooms() 
							+ "} is/are more than currently available {" 
							+ availableRoom.getCurrentAvailableRoomCount() + "} rooms.");  
		}
	}

	public PaymentDTO makePayment(String gatewayUrl, PaymentDTO paymentDTO) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			paymentDTO = restTemplate.postForObject(gatewayUrl, paymentDTO, PaymentDTO.class);
		} catch (HttpClientErrorException e) {
			throw new BookingException(HttpStatus.NOT_IMPLEMENTED.value(),
					Messages.getString("BookingServiceImpl.PAYMENT_FAILED") + e.getResponseBodyAsString());  
		}
		return paymentDTO;
	}

}
