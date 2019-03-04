package com.mindtree.mystayapp.dto;

public class PaymentDTO {

	private Long transactionId;
	private String userId;
	private Double totalAmount;
	private String vendor;
	private Double cardBalance;
	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public Double getCardBalance() {
		return cardBalance;
	}
	public void setCardBalance(Double cardBalance) {
		this.cardBalance = cardBalance;
	}
	@Override
	public String toString() {
		return "PaymentDTO [transactionId=" + transactionId + ", userId=" + userId + ", totalAmount=" + totalAmount
				+ ", vendor=" + vendor + ", cardBalance=" + cardBalance + "]";
	}
	
	
}
