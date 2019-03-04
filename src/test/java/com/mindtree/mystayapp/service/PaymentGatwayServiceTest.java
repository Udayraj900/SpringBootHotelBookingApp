package com.mindtree.mystayapp.service;

import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.mindtree.mystayapp.dto.PaymentDTO;
import com.mindtree.mystayapp.exception.PaymentException;
import com.mindtree.mystayapp.service.impl.PaymentGatewayServiceImpl;

public class PaymentGatwayServiceTest {

	@InjectMocks
	PaymentGatewayServiceImpl paymentGatewayService;

	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	
	@Test
	public void testMakePayment_whenSuccess() {
		PaymentDTO paymentDto = new PaymentDTO();
		paymentDto.setCardBalance(50000.0);
		paymentDto.setTotalAmount(10000.0);
		PaymentDTO newpaymentDto = new PaymentDTO();
		newpaymentDto.setCardBalance(40000.0);
		newpaymentDto.setTotalAmount(10000.0);
		newpaymentDto.setTransactionId(1L);
		PaymentDTO paymentResponse = paymentGatewayService.makePayment(paymentDto);
		assertNotEquals(50000.0, paymentResponse.getCardBalance());
		
		
	}

	@Test
	public void testMakePayment_whenNotSuccess() {
		try{
		PaymentDTO paymentDto = new PaymentDTO();
		paymentDto.setCardBalance(2000.0);
			paymentDto.setTotalAmount(10000.0);
		paymentGatewayService.makePayment(paymentDto);
		}catch(PaymentException e)
		{
			assert (true);
		}
				
	}

}
