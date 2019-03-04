package com.mindtree.mystayapp.controller;

import com.mindtree.mystayapp.dto.HotelsByRoomsAvailableDTO;
import com.mindtree.mystayapp.service.SearchHotelService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import com.mindtree.mystayapp.dto.SearchRequestDTO;
import com.mindtree.mystayapp.exception.SearchHotelException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchControllerTest {
    private static final String BANGALORE = "Bangalore";
	@InjectMocks
    SearchHotelController searchHotelController;
    @Mock
    Pageable pageable;
    @Mock
    SearchHotelService hotelServiceMock;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = SearchHotelException.class)
    public void testSearchHotelWithoutProvideCity() {
        SearchRequestDTO searchRequestDTO = new SearchRequestDTO();
        searchHotelController.searchHotels(searchRequestDTO, pageable);

    }

    @Test(expected = SearchHotelException.class)
    public void testSearchHotel_WhenHotelsNotAvailable() {
        List<HotelsByRoomsAvailableDTO> hotelsByRoomsAvailableDTO = new ArrayList<>();
        Page<HotelsByRoomsAvailableDTO> pagedResponse = new PageImpl<>(hotelsByRoomsAvailableDTO);
        SearchRequestDTO searchRequestDTO = new SearchRequestDTO();
        searchRequestDTO.setCity(BANGALORE);
        Mockito.when(hotelServiceMock.searchAvailableHotels(Mockito.any(), Mockito.any()))
                .thenReturn(pagedResponse);
        searchHotelController.searchHotels(searchRequestDTO, pageable);
    }

    @Test
    public void testSearchHote_WhenHotelsWithSuccessResponse() {
        Mockito.when(hotelServiceMock.searchAvailableHotels(Mockito.any(), Mockito.any()))
                .thenReturn(buildSearchResponseDTO());
        SearchRequestDTO searchRequestDTO = new SearchRequestDTO();
        searchRequestDTO.setCity(BANGALORE);
        assertNotNull(searchHotelController.searchHotels(searchRequestDTO, pageable));
    }

    public Page<HotelsByRoomsAvailableDTO> buildSearchResponseDTO() {
        List<HotelsByRoomsAvailableDTO> hotelsByRoomsAvailableDTO = new ArrayList<>();
        HotelsByRoomsAvailableDTO hotelsByRoomsAvailableDTO1 = new HotelsByRoomsAvailableDTO();
        hotelsByRoomsAvailableDTO1.setCity(BANGALORE);
        hotelsByRoomsAvailableDTO1.setHotelName("Taj");
        hotelsByRoomsAvailableDTO1.setHotelId(123456L);
        hotelsByRoomsAvailableDTO.add(hotelsByRoomsAvailableDTO1);
    
		Page<HotelsByRoomsAvailableDTO> pagedResponse = new PageImpl<>(hotelsByRoomsAvailableDTO);
        return pagedResponse;
    }
}
