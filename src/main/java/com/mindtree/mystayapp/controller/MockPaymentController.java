package com.mindtree.mystayapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.mystayapp.dto.PaymentDTO;
import com.mindtree.mystayapp.exception.PaymentException;
import com.mindtree.mystayapp.service.PaymentGatewayService;

/**
 * @author RShaw
 * 
 *         Payment Controller: Provide the end point for the Payment API call
 */
@RestController
@RequestMapping("/paymentGateway")
public class MockPaymentController {

	@Autowired
	PaymentGatewayService service;

	@RequestMapping(value = "/doPayment", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PaymentDTO> makePayment(@RequestBody PaymentDTO paymentDto) {
		PaymentDTO dto = null;

		try {
			dto = service.makePayment(paymentDto);
		} catch (PaymentException e) {
			throw new PaymentException(HttpStatus.NOT_IMPLEMENTED.value(), e.getMessage());
		}
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
}
