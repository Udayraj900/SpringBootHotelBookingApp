package com.mindtree.mystayapp.controller;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.mystayapp.dto.HotelsByRoomsAvailableDTO;
import com.mindtree.mystayapp.dto.SearchRequestDTO;
import com.mindtree.mystayapp.exception.SearchHotelException;
import com.mindtree.mystayapp.service.SearchHotelService;

/**
 * @author RShaw
 * 
 *         Controller class to handle the hotel search operations
 *
 */
@RestController
@RequestMapping(value = "/mystayapp")
public class SearchHotelController {

	private static final Logger logger = LoggerFactory.getLogger(SearchHotelController.class);

	@Autowired
	private SearchHotelService searchHotelService;

	/**
	 * 
	 * @param searchRequest
	 * @return
	 * @throws SearchHotelException
	 * 
	 *             Controller method to handle the hotel search operations with
	 *             the user inputs like city, dates and other filters
	 * 
	 */
	@RequestMapping(value = "/hotels", method = RequestMethod.POST, produces = "application/json")
	public Page<HotelsByRoomsAvailableDTO> searchHotels(@Valid @RequestBody SearchRequestDTO searchRequest,
			Pageable pageable) throws SearchHotelException {
		logger.info("Started searching the available hotels in the city: " + searchRequest.getCity());

		if (StringUtils.isEmpty(searchRequest.getCity())) {
			logger.info("City name is require, please provide city name to search hotels.");
			throw new SearchHotelException(HttpStatus.UNPROCESSABLE_ENTITY.value(),
					"City name is require, please provide city name to search hotels.");
		}
		try {
			Page<HotelsByRoomsAvailableDTO> availableHotels = searchHotelService.searchAvailableHotels(searchRequest,
					pageable);
			if (availableHotels.getTotalElements() == 0) {
				logger.info("Searching completed succesfully. No Hotels found for the provided inputs.");
				throw new SearchHotelException(HttpStatus.OK.value(), "No Hotels found for the provided inputs.");
			}
			logger.info("Searching completed succesfully...");
			return availableHotels;

		} catch (SearchHotelException e) {
			logger.error("Search for hotels failed. Please investigate - search request :" + searchRequest);
			throw new SearchHotelException(HttpStatus.NO_CONTENT.value(), e.getMessage());

		}
	}

}
