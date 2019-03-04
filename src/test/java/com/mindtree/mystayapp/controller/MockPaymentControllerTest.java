package com.mindtree.mystayapp.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.mystayapp.dto.PaymentDTO;
import com.mindtree.mystayapp.exception.PaymentException;
import com.mindtree.mystayapp.service.PaymentGatewayService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MockPaymentControllerTest {

	@InjectMocks
	MockPaymentController mockPaymentController;

	@Mock
	PaymentGatewayService paymentGatewayService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test(expected = PaymentException.class)
	public void testMakePayment_PaymentNotSuccessfull() {
		PaymentDTO paymentDto = new PaymentDTO();
		paymentDto.setCardBalance(2000.0);
		paymentDto.setTotalAmount(10000.0);
		Mockito.when(paymentGatewayService.makePayment(Mockito.any(PaymentDTO.class))).thenThrow(PaymentException.class);
		ResponseEntity<PaymentDTO> paymentResponse = mockPaymentController.makePayment(paymentDto);
		assertEquals(204, paymentResponse.getStatusCodeValue());

	}

	@Test
	public void testMakePayment_PaymentSuccefull() {
		PaymentDTO paymentDto = new PaymentDTO();
		paymentDto.setCardBalance(50000.0);
		paymentDto.setTotalAmount(10000.0);
		paymentDto.setTransactionId(1L);
		Mockito.when(paymentGatewayService.makePayment(Mockito.any(PaymentDTO.class))).thenReturn(paymentDto);
		ResponseEntity<PaymentDTO> paymentResponse = mockPaymentController.makePayment(paymentDto);
		assertEquals(200, paymentResponse.getStatusCodeValue());
	}

	


}
