package com.mindtree.mystayapp.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mindtree.mystayapp.dto.HotelsByRoomsAvailableDTO;
import com.mindtree.mystayapp.dto.SearchRequestDTO;
import com.mindtree.mystayapp.exception.SearchHotelException;

public interface SearchHotelService {

	public Page<HotelsByRoomsAvailableDTO> searchAvailableHotels(SearchRequestDTO searchRequest,Pageable pageable) throws SearchHotelException;
	
	

}
