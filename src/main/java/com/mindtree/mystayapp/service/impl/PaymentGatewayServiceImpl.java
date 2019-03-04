package com.mindtree.mystayapp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mindtree.mystayapp.dto.PaymentDTO;
import com.mindtree.mystayapp.exception.PaymentException;
import com.mindtree.mystayapp.service.PaymentGatewayService;

@Service
public class PaymentGatewayServiceImpl implements PaymentGatewayService{

	private static final Logger logger = LoggerFactory.getLogger(PaymentGatewayServiceImpl.class);

	@Override
	public PaymentDTO makePayment(PaymentDTO paymentDto) {
		try{
			
			PaymentDTO newPayment = new PaymentDTO();
			if(paymentDto.getCardBalance()<paymentDto.getTotalAmount()){
				throw new PaymentException(HttpStatus.NOT_IMPLEMENTED.value(),"Insufficient Balance");
			}
			else{
				newPayment.setTotalAmount(paymentDto.getTotalAmount());
				newPayment.setCardBalance(paymentDto.getCardBalance()-paymentDto.getTotalAmount());
				newPayment.setTransactionId((Math.round(Math.random())));
				newPayment.setUserId(paymentDto.getUserId());
				newPayment.setVendor(paymentDto.getVendor());
				
				return newPayment;
			}
			
		}catch(PaymentException e){
			logger.error("Payment not completed successfully.");
			throw new PaymentException(HttpStatus.NOT_IMPLEMENTED.value(),"Payment not completed successfully.");
		}
		
	}

}
