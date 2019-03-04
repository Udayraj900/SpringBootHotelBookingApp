package com.mindtree.mystayapp.service.implhelper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static java.util.Objects.nonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.mindtree.mystayapp.dao.HotelRepository;
import com.mindtree.mystayapp.dto.AvailableRoomsDTO;
import com.mindtree.mystayapp.dto.HotelsByRoomsAvailableDTO;
import com.mindtree.mystayapp.dto.SearchRequestDTO;
import com.mindtree.mystayapp.model.Hotel;

@Component
public class SearchHotelServiceImplHelper {

	@Autowired
	private HotelRepository hotelRepository;

	public Page<HotelsByRoomsAvailableDTO> getAvailableHotelsByRequest(SearchRequestDTO searchRequest,
			Pageable pageable) {
		Page<HotelsByRoomsAvailableDTO> hotelrooms;

		if (isNotEmpty(searchRequest.getRoomType())) {
			hotelrooms = prepareJsonResponseForHotels(hotelRepository.findByCityAndRoomType(searchRequest.getCity(),
					searchRequest.getFromDate(), searchRequest.getToDate(), searchRequest.getRoomType()), pageable);
			return (hotelrooms);
		}
		if (nonNull(searchRequest.getPrice())) {
			hotelrooms = prepareJsonResponseForHotels(hotelRepository.findByCityAndPrice(searchRequest.getCity(),
					searchRequest.getFromDate(), searchRequest.getToDate(), searchRequest.getPrice()), pageable);
			return (hotelrooms);
		}
		if (nonNull(searchRequest.getDistance())) {

			hotelrooms = prepareJsonResponseForHotels(hotelRepository.findByCityAndDistance(searchRequest.getCity(),
					searchRequest.getFromDate(), searchRequest.getToDate(), searchRequest.getDistance()), pageable);
			return (hotelrooms);
		}
		hotelrooms = prepareJsonResponseForHotels(hotelRepository.findByCityAndDates(searchRequest.getCity(),
				searchRequest.getFromDate(), searchRequest.getToDate()), pageable);

		return (hotelrooms);

	}

	/**
	 * @param searchRequest
	 * @return Preparing a unique List of all available hotels containing their
	 *         room details.
	 */
	private Page<HotelsByRoomsAvailableDTO> prepareJsonResponseForHotels(List<AvailableRoomsDTO> rooms,
			Pageable pageable) {
		Set<Long> ids = rooms.stream().map(r -> r.getHotelId()).collect(Collectors.toSet());
		List<Long> hotelsIds = ids.stream().collect(Collectors.toList());
		Page<Hotel> hotels = hotelRepository.findAllByHotelId(hotelsIds, pageable);

		Page<HotelsByRoomsAvailableDTO> availableHotels = hotels.map(hotel -> {
			HotelsByRoomsAvailableDTO availableHotelsbyRoom = new HotelsByRoomsAvailableDTO();
			List<AvailableRoomsDTO> roomList = rooms.stream().filter(r -> r.getHotelId().equals(hotel.getHotelId()))
					.collect(Collectors.toList());
			availableHotelsbyRoom.setHotelId(hotel.getHotelId());
			availableHotelsbyRoom.setHotelName(hotel.getHotelName());
			availableHotelsbyRoom.setCity(hotel.getCity());
			availableHotelsbyRoom.setDistanceFromAirport(hotel.getDistanceFromAirport());
			availableHotelsbyRoom.setHotelPhoneNo(hotel.getHotelPhoneNo());
			availableHotelsbyRoom.setAvailableRooms(roomList);
			return availableHotelsbyRoom;
		});

		return availableHotels;
	}
}
