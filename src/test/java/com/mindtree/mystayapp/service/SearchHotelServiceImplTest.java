package com.mindtree.mystayapp.service;

import static java.lang.System.currentTimeMillis;
import static org.junit.Assert.assertNotNull;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.mystayapp.dto.HotelsByRoomsAvailableDTO;
import com.mindtree.mystayapp.dto.SearchRequestDTO;
import com.mindtree.mystayapp.exception.SearchHotelException;
import com.mindtree.mystayapp.service.impl.SearchHotelServiceImpl;
import com.mindtree.mystayapp.service.implhelper.SearchHotelServiceImplHelper;
import com.mindtree.mystayapp.util.DateUtil;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchHotelServiceImplTest {
    @InjectMocks
    SearchHotelServiceImpl searchHotelServiceImpl;
    @Mock
    Pageable pageable;
    @Mock
    SearchHotelServiceImplHelper searchHotelImplHelperMock;

    @Test
    public void testSearchHotelService_WithoutProvideDates(){
        SearchRequestDTO searchRequestDTO = new SearchRequestDTO();
        List<HotelsByRoomsAvailableDTO> hotelsByRoomsAvailableDTO = new ArrayList<>();
        Page<HotelsByRoomsAvailableDTO> pagedResponse = new PageImpl<>(hotelsByRoomsAvailableDTO);
        Mockito.when(searchHotelImplHelperMock.getAvailableHotelsByRequest(Mockito.any(), Mockito.any()))
                .thenReturn(pagedResponse);
        assertNotNull(searchHotelServiceImpl.searchAvailableHotels(searchRequestDTO, pageable));
    }

    @Test(expected = SearchHotelException.class)
    public void testSearchHotelService_WithInvalidDates(){
        SearchRequestDTO searchRequestDTO = new SearchRequestDTO();
        Date currentDate = new Date(currentTimeMillis());
        Date fromDate = new Date(DateUtil.addDays(currentDate, -2).getTime());
        searchRequestDTO.setFromDate(fromDate);
        searchRequestDTO.setToDate(currentDate);
        searchHotelServiceImpl.searchAvailableHotels(searchRequestDTO, pageable);

    }

}
