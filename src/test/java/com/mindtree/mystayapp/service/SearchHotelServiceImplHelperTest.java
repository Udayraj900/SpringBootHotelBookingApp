package com.mindtree.mystayapp.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.mystayapp.dao.HotelRepository;
import com.mindtree.mystayapp.dto.AvailableRoomsBySearchDTO;
import com.mindtree.mystayapp.dto.AvailableRoomsDTO;
import com.mindtree.mystayapp.dto.HotelsByRoomsAvailableDTO;
import com.mindtree.mystayapp.dto.SearchRequestDTO;
import com.mindtree.mystayapp.model.Hotel;
import com.mindtree.mystayapp.model.Room;
import com.mindtree.mystayapp.service.implhelper.SearchHotelServiceImplHelper;
import com.mindtree.mystayapp.util.DateUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchHotelServiceImplHelperTest {

	@MockBean
	private HotelRepository hotelRepository;

	@Autowired
	private SearchHotelServiceImplHelper helper;

	private Hotel mockHotel1;
	@SuppressWarnings("unused")
	private Hotel mockHotel2;
	private Room mockRoom1;
	private Room mockRoom2;

	private List<Hotel> hotelList = new ArrayList<Hotel>();
	private List<Room> roomList = new ArrayList<Room>();
	private SearchRequestDTO requestDTO = new SearchRequestDTO();

	private Page<HotelsByRoomsAvailableDTO> responseDTO;
	private Page<Hotel> pageHotels;

	private HotelsByRoomsAvailableDTO hotelAvailable = new HotelsByRoomsAvailableDTO();
	private AvailableRoomsDTO availableRoom = new AvailableRoomsDTO();
	private List<HotelsByRoomsAvailableDTO> availableRoomList = new ArrayList<>();
	private List<AvailableRoomsDTO> roomsList = new ArrayList<>();

	private Date currentDate = new Date(System.currentTimeMillis());
	private Date fromDate;
	private Date toDate;
	private Set<Long> ids = new HashSet<>();
	@SuppressWarnings("unused")
	private List<Long> hotelsIds = new ArrayList<>();
	List<AvailableRoomsBySearchDTO> rooms = new ArrayList<>();
	AvailableRoomsBySearchDTO room1;

	@Before
	public void init() {

		mockHotel1 = new Hotel(1L, "By The Way", "9876543120", "Bangalore", 50);
		mockHotel2 = new Hotel(2L, "The Taj", "9876543120", "Delhi", 10);

		mockRoom1 = new Room(101L, "Luxury", 1000.00, 2);
		mockRoom2 = new Room(102L, "Semi-Luxury", 800.00, 3);

		hotelList.add(mockHotel1);
		mockRoom1.setHotel(mockHotel1);
		mockRoom2.setHotel(mockHotel1);
		roomList.add(mockRoom1);
		roomList.add(mockRoom2);
		fromDate = new Date(DateUtil.addDays(currentDate, 1).getTime());
		toDate = new Date(DateUtil.addDays(currentDate, 2).getTime());
		requestDTO.setCity("Bengaluru");
		requestDTO.setFromDate(fromDate);
		requestDTO.setToDate(toDate);
		
		
		requestDTO.setRoomType(null);
		requestDTO.setPrice(null);
		requestDTO.setDistance(null);
	
		availableRoom.setHotelId(mockHotel1.getHotelId());
		availableRoom.setRoomId(mockRoom1.getRoomId());
		availableRoom.setRoomType(mockRoom1.getRoomType());
		availableRoom.setRoomPrice(mockRoom1.getRoomPrice());
		availableRoom.setTotalRoomCount(mockRoom1.getTotalRoomsCount());
		availableRoom.setCurrentAvailableRoomCount(1L);
		roomsList.add(availableRoom);
	
		hotelAvailable.setHotelId(mockHotel1.getHotelId());
		hotelAvailable.setHotelName(mockHotel1.getHotelName());
		hotelAvailable.setHotelPhoneNo(mockHotel1.getHotelPhoneNo());
		hotelAvailable.setCity(mockHotel1.getCity());
		hotelAvailable.setDistanceFromAirport(mockHotel1.getDistanceFromAirport());
		hotelAvailable.setAvailableRooms(roomsList);
		availableRoomList.add(hotelAvailable);
		responseDTO = new PageImpl<>(availableRoomList);
		pageHotels = new PageImpl<>(hotelList);
		ids = roomsList.stream().map(r -> r.getHotelId()).collect(Collectors.toSet());
		hotelsIds = ids.stream().collect(Collectors.toList());
	}
	
	

	@Test
	public void testGetAvailableHotelsByRequest_IfRoomTypeAvailable() {
		requestDTO.setRoomType("Luxury");
		
		Mockito.when(hotelRepository.findByCityAndRoomType(Mockito.anyString(), Mockito.any(), Mockito.any(),
				Mockito.anyString())).thenReturn(roomsList);
		when(hotelRepository.findAllByHotelId(Mockito.any(), Mockito.any())).thenReturn(pageHotels);
		assertEquals(responseDTO.getNumberOfElements(), helper.getAvailableHotelsByRequest(requestDTO, null).getNumberOfElements());
	}

	@Test
	public void testGetAvailableHotelsByRequest_IfRoomPriceAvailable() {
		requestDTO.setPrice(1000.0);
		Mockito.when(hotelRepository.findByCityAndPrice(Mockito.anyString(), Mockito.any(), Mockito.any(),
				Mockito.anyDouble())).thenReturn(roomsList);
		when(hotelRepository.findAllByHotelId(Mockito.any(), Mockito.any())).thenReturn(pageHotels);
		assertEquals(responseDTO.getNumberOfElements(), helper.getAvailableHotelsByRequest(requestDTO, null).getNumberOfElements());

	}

	@Test
	public void testGetAvailableHotelsByRequest_IfRoomDistanceAvailable() {
		requestDTO.setDistance(10);
		Mockito.when(hotelRepository.findByCityAndDistance(Mockito.anyString(), Mockito.any(), Mockito.any(),
				Mockito.anyInt())).thenReturn(roomsList);
		when(hotelRepository.findAllByHotelId(Mockito.any(), Mockito.any())).thenReturn(pageHotels);
		assertEquals(responseDTO.getNumberOfElements(), helper.getAvailableHotelsByRequest(requestDTO, null).getNumberOfElements());

	}

	@Test
	public void testGetAvailableHotelsByRequest_IfOnlyCityAvailable() {

		Mockito.when(hotelRepository.findByCityAndDates(Mockito.anyString(), Mockito.any(), Mockito.any())).thenReturn(roomsList);
		when(hotelRepository.findAllByHotelId(Mockito.any(), Mockito.any())).thenReturn(pageHotels);
		assertEquals(responseDTO.getNumberOfElements(), helper.getAvailableHotelsByRequest(requestDTO, null).getNumberOfElements());
	}
}
