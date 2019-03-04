package com.mindtree.mystayapp.service;

import com.mindtree.mystayapp.dto.PaymentDTO;

public interface PaymentGatewayService {

	public PaymentDTO makePayment(PaymentDTO paymentDto);

}
