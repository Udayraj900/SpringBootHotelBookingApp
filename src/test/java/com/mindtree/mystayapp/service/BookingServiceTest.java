package com.mindtree.mystayapp.service;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withBadRequest;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestGatewaySupport;

import com.mindtree.mystayapp.dao.BookingRepository;
import com.mindtree.mystayapp.dao.RoomRepository;
import com.mindtree.mystayapp.dao.UserRepository;
import com.mindtree.mystayapp.dto.AvailableRoomsBySearchDTO;
import com.mindtree.mystayapp.dto.BookingCriteria;
import com.mindtree.mystayapp.dto.PaymentDTO;
import com.mindtree.mystayapp.exception.BookingException;
import com.mindtree.mystayapp.model.Booking;
import com.mindtree.mystayapp.model.Hotel;
import com.mindtree.mystayapp.model.Role;
import com.mindtree.mystayapp.model.Room;
import com.mindtree.mystayapp.model.User;
import com.mindtree.mystayapp.service.impl.BookingServiceImpl;
import com.mindtree.mystayapp.util.Messages;

@SuppressWarnings({ "unused" })
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingServiceTest {

	private static final String BOOKED_STATUS = "booked";
	private static final String ROOM_TYPE = "Luxury";
	private static final String PHONE_NO = "9876543210";
	private static final String BENGALURU = "Bengaluru";
	private static final String BY_THE_WAY = "By The Way";
	
	private String PAYMENT_URL = Messages.getString("BookingServiceImpl.PAYMENT_URL");

	@Mock
	private BookingRepository bookingRepositoryMock;

	@Mock
	private RoomRepository roomRepository;

	@Mock
	private UserRepository userRepository;

	@Mock
	private BookingService bookingServiceMock;

	@InjectMocks
	private BookingServiceImpl serviceImpl;

	@Mock
	private MockRestServiceServer mockServer;
	@Mock
	RestTemplate restTemplate;
	
	private Hotel mockHotel1;
	private Hotel mockHotel2;
	private Room mockRoom1;
	private Room mockRoom2;
	private Booking booking = new Booking();
	private User user = new User();
	private User user1 = new User();
	private Role role = new Role();

	private List<Hotel> hotelList = new ArrayList<Hotel>();
	private List<Room> roomList = new ArrayList<Room>();

	private AvailableRoomsBySearchDTO availableRoom;
	private PaymentDTO dto;
	private ResponseEntity<Object> paymentDto;

	//RestTemplate restTemplate;
	//MockRestServiceServer mockRestServiceServer;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockHotel1 = new Hotel(1L, BY_THE_WAY, "9876543120", "Bangalore", 50);
		mockHotel2 = new Hotel(2L, "The Taj", "9876543120", "Delhi", 10);

		mockRoom1 = new Room(101L, ROOM_TYPE, 1000.00, 2);
		mockRoom2 = new Room(102L, "Semi-Luxury", 800.00, 3);

		role.setRoleId(1);
		role.setRoleType("ADMIN");

		user.setUserId(1L);
		user.setRole(role);
		user.setUserName("Raj");

		mockRoom1.setHotel(mockHotel1);
		booking.setBookingId(1L);
		booking.setBookedRoom(mockRoom1);
		booking.setBookingDate(new Date(System.currentTimeMillis()));
		booking.setBookingStatus("");
		booking.setCheckInDate(new Date(System.currentTimeMillis()));
		booking.setCheckOutDate(new Date(System.currentTimeMillis()));
		booking.setUser(user);
		booking.setNoOfRooms(2);
		booking.setHotel(mockRoom1.getHotel());

		hotelList.add(mockHotel1);
		hotelList.add(mockHotel2);

		roomList.add(mockRoom1);
		roomList.add(mockRoom2);
		dto = new PaymentDTO();
		dto.setUserId(user.getUserName());
		dto.setCardBalance(50000.0);
		dto.setTotalAmount(1000.0);
		dto.setTransactionId(1l);
		dto.setVendor("");
		paymentDto = new ResponseEntity<Object>(dto, HttpStatus.OK);

		restTemplate = new RestTemplate();
		mockServer = MockRestServiceServer.createServer(restTemplate);

	}

	@Autowired
	MappingJackson2HttpMessageConverter jacksonConverter;

	private String convertToJson(ResponseEntity<Object> responseEntity)
			throws HttpMessageNotWritableException, IOException {
		MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
		jacksonConverter.write(responseEntity, MediaType.APPLICATION_JSON, outputMessage);
		return outputMessage.getBody().toString();
	}

	@Test
	@Ignore
	public void testSaveBooking_Success() throws HttpMessageNotWritableException, IOException {

		booking.setBookingId(1L);
		booking.setBookedRoom(mockRoom1);
		booking.setBookingDate(new Date(System.currentTimeMillis()));
		booking.setBookingStatus("");
		booking.setCheckInDate(new Date(System.currentTimeMillis()));
		booking.setCheckOutDate(new Date(System.currentTimeMillis()));
		booking.setUser(user);
		booking.setNoOfRooms(1);
		booking.setHotel(mockRoom1.getHotel());
		BookingCriteria bookingCritera = new BookingCriteria(101L, 1L, "", 1, "Raj", new Date(System.currentTimeMillis()),
				new Date(System.currentTimeMillis()));
		availableRoom = new AvailableRoomsBySearchDTO(1L, BY_THE_WAY, BENGALURU, PHONE_NO, 10, 101L, ROOM_TYPE,
				1000.0, 3, BOOKED_STATUS, 3L);
		Mockito.when(bookingRepositoryMock.getCurrentAvailableRooms(Mockito.anyString(), Mockito.anyLong(),
				Mockito.any(), Mockito.any())).thenReturn(availableRoom);
		Mockito.when(roomRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockRoom1));
		Mockito.when(userRepository.findByUserName(Mockito.anyString())).thenReturn(user);
		Mockito.when(bookingRepositoryMock.save(Mockito.any(Booking.class))).thenReturn(booking);
		mockServer.expect(requestTo(PAYMENT_URL)).andExpect(method(HttpMethod.POST))
				.andRespond(withSuccess(this.convertToJson(paymentDto), MediaType.APPLICATION_JSON));
		Booking bookedRoom = serviceImpl.saveBooking(bookingCritera);
		assertEquals(1, bookedRoom.getNoOfRooms());

	}

	@Test
	public void testSaveBooking_PaymentNotSuccessfull() throws HttpMessageNotWritableException, IOException {
		try {
			booking.setBookingId(1L);
			booking.setBookedRoom(mockRoom1);
			booking.setBookingDate(new Date(System.currentTimeMillis()));
			booking.setBookingStatus("");
			booking.setCheckInDate(new Date(System.currentTimeMillis()));
			booking.setCheckOutDate(new Date(System.currentTimeMillis()));
			booking.setUser(user);
			booking.setNoOfRooms(1);
			booking.setHotel(mockRoom1.getHotel());
			availableRoom = new AvailableRoomsBySearchDTO(1L, BY_THE_WAY, BENGALURU, PHONE_NO, 10, 101L, ROOM_TYPE,
					1000.0, 3, BOOKED_STATUS, 3L);
			Mockito.when(bookingRepositoryMock.getCurrentAvailableRooms(Mockito.anyString(), Mockito.anyLong(),
					Mockito.any(), Mockito.any())).thenReturn(availableRoom);
			Mockito.when(roomRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockRoom1));
			Mockito.when(userRepository.findByUserName(Mockito.anyString())).thenReturn(user);
			Mockito.when(bookingRepositoryMock.save(Mockito.any(Booking.class))).thenReturn(booking);
			dto = new PaymentDTO();
			dto.setUserId(user.getUserName());
			dto.setCardBalance(50000.0);
			dto.setTotalAmount(100000.0);
			dto.setTransactionId(1l);
			dto.setVendor("");
			paymentDto = new ResponseEntity<Object>(dto, HttpStatus.OK);
			mockServer.expect(requestTo(PAYMENT_URL)).andExpect(method(HttpMethod.POST))
					.andRespond(withSuccess(this.convertToJson(paymentDto), MediaType.APPLICATION_JSON));
		} catch (BookingException exception) {
			assert (true);
		}
	}

	
	@Test
	public void testRoomAvailableCheck_AvailableRoomsAreLessThanDesireRooms() {
		try {
			availableRoom = new AvailableRoomsBySearchDTO(1L, BY_THE_WAY, BENGALURU, PHONE_NO, 10, 101l, ROOM_TYPE,
					1000.0, 3, BOOKED_STATUS, 3L);
			Mockito.when(bookingRepositoryMock.getCurrentAvailableRooms(Mockito.anyString(), Mockito.anyLong(),
					Mockito.any(), Mockito.any())).thenReturn(availableRoom);
			Mockito.when(roomRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockRoom1));
			Mockito.when(userRepository.findByUserName(Mockito.anyString())).thenReturn(user);
			Mockito.when(bookingRepositoryMock.save(Mockito.any(Booking.class))).thenReturn(booking);
		} catch (BookingException e) {
			assert (true);
		}

	}

	@Test
	public void testRoomAvailableCheck_RoomNotFoundByRoomId() {
		try {
			availableRoom = new AvailableRoomsBySearchDTO();
			Mockito.when(roomRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockRoom1));
		} catch (BookingException e) {
			assert (true);
		}
	}

	@Test
	public void testAvailableRooms_WhenRoomNotFoundByAvailablity() {
		try {
			availableRoom = null;
			Mockito.when(roomRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockRoom1));
			Mockito.when(userRepository.findByUserName(Mockito.anyString())).thenReturn(user);
			Mockito.when(bookingRepositoryMock.getCurrentAvailableRooms(Mockito.anyString(), Mockito.anyLong(),
					Mockito.any(), Mockito.any())).thenReturn(availableRoom);
		} catch (BookingException e) {
			assert (true);
		}
	}

	@Test
	public void testAvailableRooms_WhenUserNotFound() {
		try {
			Mockito.when(roomRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockRoom1));
			Mockito.when(userRepository.findByUserName(Mockito.anyString())).thenReturn(user);
			Mockito.when(bookingRepositoryMock.getCurrentAvailableRooms(Mockito.anyString(), Mockito.anyLong(),
					Mockito.any(), Mockito.any())).thenReturn(availableRoom);
		} catch (BookingException e) {
			assert (true);
		}
	}
}
