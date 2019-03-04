package com.mindtree.mystayapp.service.impl;


import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mindtree.mystayapp.dto.HotelsByRoomsAvailableDTO;
import com.mindtree.mystayapp.dto.SearchRequestDTO;
import com.mindtree.mystayapp.exception.SearchHotelException;
import com.mindtree.mystayapp.service.SearchHotelService;
import com.mindtree.mystayapp.service.implhelper.SearchHotelServiceImplHelper;
import com.mindtree.mystayapp.util.DateUtil;

@Service
public class SearchHotelServiceImpl implements SearchHotelService {

	private static final Logger logger = LoggerFactory.getLogger(SearchHotelServiceImpl.class);

	@Autowired
	private SearchHotelServiceImplHelper searchHotelServiceImplHelper; 

	Pageable pageable;
	
	@Override
	public Page<HotelsByRoomsAvailableDTO> searchAvailableHotels(SearchRequestDTO searchRequest,Pageable pageable) throws SearchHotelException {

		logger.info("In Service layer started...");
		Date currentDate = new Date(System.currentTimeMillis());
		Date fromDate = new Date(DateUtil.addDays(currentDate, 1).getTime());
		Date toDate = new Date(DateUtil.addDays(currentDate, 2).getTime());

		if (searchRequest.getFromDate() != null && searchRequest.getToDate() != null) {
			fromDate = searchRequest.getFromDate();
			toDate = searchRequest.getToDate();
			if (fromDate.before(new Date(System.currentTimeMillis())) || (fromDate.after(toDate))) {
				logger.debug("Dates are invalid. check-in date should be greater then current date and less then check-out date.");
				throw new SearchHotelException(HttpStatus.BAD_REQUEST.value(),
						"Dates are invalid. check-in date should be greater then current date and less then check-out date.");
			}

		}else{
		searchRequest.setFromDate(fromDate);
		searchRequest.setToDate(toDate);
		}
		return searchHotelServiceImplHelper.getAvailableHotelsByRequest(searchRequest,pageable);
	}
	
}
